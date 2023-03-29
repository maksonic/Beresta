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
import ru.maksonic.beresta.feature.notes_list.api.NewFolderDialogSharedState
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.NotesListSharedScrollState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.presentation.core.Eff
import ru.maksonic.beresta.screen.main.presentation.core.MainSandbox
import ru.maksonic.beresta.screen.main.presentation.core.Model
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar.MainBottomBar
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.functional.*

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

    HandleUiEffects(sandbox.effects, router)

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

        val sharedFolderDialogState = NewFolderDialogSharedState(
            isVisible = model.isVisibleNewFolderDialog,
            folderInputName = model.newFolderInputName,
            updateInputField = { name -> sandbox.send(Msg.Inner.UpdateNewFolderNameInput(name)) },
            onCreateNewFolderClicked = { sandbox.send(Msg.Ui.OnCreateNewNotesFolderClicked) },
            onDismissClicked = { sandbox.send(Msg.Ui.OnDismissFolderCreationDialogClicked) }
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
            isShowUnpinItem = model.isShowBottomBarUnpinBtn
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

        notesList.FolderCreationDialog(sharedFolderDialogState)
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
        onNoteClicked = { noteId -> send(Msg.Ui.OnNoteClicked(noteId)) },
        onNoteLongPressed = { noteId -> send(Msg.Ui.OnNoteLongPressed(noteId)) },
        onChipFilterClicked = { id -> send(Msg.Ui.OnChipFilterClicked(id)) },
        onAddNewFilterFolderClicked = { send(Msg.Ui.OnCreateNewNotesFolderClicked) },
        sharedScroll = sharedScrollState
    )
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: MainScreenRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateToAddNewNote -> router.toNoteEditor(0)
            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.ShowNoteForEdit -> router.toNoteEditor(eff.id)
        }
    }
}