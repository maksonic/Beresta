package ru.maksonic.beresta.screen.main.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListSharedScrollState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.presentation.core.Eff
import ru.maksonic.beresta.screen.main.presentation.core.MainSandbox
import ru.maksonic.beresta.screen.main.presentation.core.Model
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar.MainBottomBar
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.*
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 15.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun MainScreen(router: MainScreenRouter) {
    Content(router = router)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    notesList: NotesListApi.Ui = get(),
    searchBar: SearchBarApi.Ui = get(),
    editNote: EditNoteApi.Ui = get(),
    filters: FoldersListApi.Ui = get(),
    panelCounter: SelectedItemsPanelUiApi = get(),
    sandbox: MainSandbox = koinViewModel(),
    router: MainScreenRouter
) {
    val model = sandbox.model.collectAsStateWithLifecycle().value
    val scrollState = rememberLazyStaggeredGridState()
    val isScrollUp = scrollState.isScrollUp()
    val isVisibleFirstNote = scrollState.isVisibleFirstItem()
    val isVisibleFirstNoteOffset = scrollState.isVisibleFirstItemOffset()
    val isExpandedSearchBar = searchBar.sharedExpandSearchState.collectAsState()
    val isVisibleNewNoteFab = !(model.isSelectionState || isExpandedSearchBar.value)
    val filtersUiSharedState = filters.sharedUiState.state.collectAsState().value
    val isVisibleNewFolderDialog = filtersUiSharedState.isVisibleDialog

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        updateCurrentSelectedFolder = { id ->
            filters.sharedUiState.updateState { old -> old.copy(currentFolderId = id) }
        }
    )

    LaunchedEffect(isVisibleNewFolderDialog) {
        sandbox.send(Msg.Inner.UpdatedNewFolderDialogVisibility(isVisibleNewFolderDialog))
    }

    LaunchedEffect(filtersUiSharedState.currentFolderId) {
        sandbox.send(Msg.Inner.UpdateCurrentSelectedFolder(filtersUiSharedState.currentFolderId))
    }

    BackHandler(model.isSelectionState) {
        sandbox.send(Msg.Ui.OnCancelSelectionClicked)
    }

    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        val sharedScrollState = NotesListSharedScrollState(
            state = { scrollState },
            isVisibleFirstNote = { isVisibleFirstNote.value },
            isVisibleFirstNoteOffset = { isVisibleFirstNoteOffset.value },
            isScrollUp = { isScrollUp },
            isSelectionState = { model.isSelectionState },
            gridCellsCount = { model.notesGridCount }
        )

        when {
            model.base.isLoading -> LoadingViewState()
            model.base.isSuccessLoading -> {
                NotesList(
                    feature = notesList,
                    model = model,
                    send = sandbox::send,
                    sharedScrollState = sharedScrollState
                )
            }
        }
        MainBottomBar(
            state = model.bottomBarState,
            send = sandbox::send,
            isScrollUp = { isScrollUp },
            isSelectionState = { model.isSelectionState },
            selectedCount = { model.selectedNotesCount },
            removedCount = { model.removedNotes.count() },
            isShowUnpinItem = model.isShowBottomBarUnpinBtn,
            isVisibleUndoRemoveNotesSnack = model.isVisibleUndoRemoveNotesSnack,
            panelCounterApi = panelCounter
        )
        searchBar.Widget(
            notesCollection = model.notes,
            sharedScroll = sharedScrollState
        )

        editNote.NewNoteFabWidget(
            isVisible = { isVisibleNewNoteFab },
            isNotesScrollUp = { isScrollUp },
            modifier
        )

        AnimateFadeInOut(isVisibleNewFolderDialog) {
            filters.FolderCreationDialog()
        }
    }
}

@Composable
private fun NotesList(
    feature: NotesListApi.Ui,
    model: Model,
    send: SendMessage,
    sharedScrollState: NotesListSharedScrollState
) {
    feature.FetchedNotesWidget(
        notes = model.notes,
        chips = model.filters,
        currentSelectedChipId = model.currentSelectedFolderId,
        selectedNotes = model.selectedNotes,
        onNoteClicked = { noteId -> send(Msg.Ui.OnNoteClicked(noteId)) },
        onNoteLongPressed = { noteId -> send(Msg.Ui.OnNoteLongPressed(noteId)) },
        onChipFilterClicked = { id -> send(Msg.Ui.OnChipFilterClicked(id)) },
        sharedScroll = sharedScrollState
    )
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: MainScreenRouter,
    updateCurrentSelectedFolder: (id: Long) -> Unit
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateToAddNewNote -> router.toNoteEditor(0)
            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.ShowNoteForEdit -> router.toNoteEditor(eff.id)
            is Eff.NavigateToFoldersList -> router.toFoldersList()
            is Eff.UpdateFolderSelection -> updateCurrentSelectedFolder(eff.currentSelectedId)
        }
    }
}