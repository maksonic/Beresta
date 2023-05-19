package ru.maksonic.beresta.screen.main.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.folders.api.ui.updateCurrentSelectedFolder
import ru.maksonic.beresta.feature.notes.list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListSharedUiState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.Eff
import ru.maksonic.beresta.screen.main.MainSandbox
import ru.maksonic.beresta.screen.main.Model
import ru.maksonic.beresta.screen.main.Msg
import ru.maksonic.beresta.screen.main.ui.widget.MainBottomBar
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 24.04.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun MainScreen(router: MainScreenRouter) {
    MainContainer(router = router)
}

@Composable
private fun MainContainer(
    sandbox: MainSandbox = koinViewModel(),
    notesListFeatureApi: NotesListApi.Ui = get(),
    notesFoldersFeatureApi: FoldersListApi.Ui = get(),
    editNoteFeatureApi: EditNoteApi.Ui = get(),
    searchBarFeatureApi: SearchBarApi.Ui = get(),
    router: MainScreenRouter
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        foldersSharedUiState = notesFoldersFeatureApi.sharedUiState
    )

    MainContent(
        model = model.value,
        send = sandbox::send,
        notesListFeatureApi = notesListFeatureApi,
        notesFoldersFeatureApi = notesFoldersFeatureApi,
        editNoteFeatureApi = editNoteFeatureApi,
        searchBarApi = searchBarFeatureApi,
        router = router
    )
}

@Composable
private fun MainContent(
    model: Model,
    send: SendMessage,
    notesListFeatureApi: NotesListApi.Ui,
    notesFoldersFeatureApi: FoldersListApi.Ui,
    editNoteFeatureApi: EditNoteApi.Ui,
    searchBarApi: SearchBarApi.Ui,
    router: MainScreenRouter,
    modifier: Modifier = Modifier
) {
    val notesListSharedState = notesListFeatureApi.sharedUiState.state.collectAsState()
    val notesFoldersSharedState = notesFoldersFeatureApi.sharedUiState.state.collectAsState()
    val searchBarSharedUiState = searchBarApi.searchBarSharedUiState.state.collectAsState()

    LaunchedEffect(notesListSharedState.value.isSelectionState) {
        send(Msg.Inner.UpdateBottomPanelState(notesListSharedState.value.isSelectionState))
    }

    LaunchedEffect(notesFoldersSharedState.value.currentFolderId) {
        send(Msg.Inner.UpdateCurrentSelectedFolder(notesFoldersSharedState.value.currentFolderId))
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        NotesListLayer(
            send = send,
            chips = model.filters.copy(data = model.filters.data.map { chip ->
                applyInitialChipTitleForLanguage(chip)
            }),
            notesList = notesListFeatureApi,
            notesFolders = notesFoldersFeatureApi,
            router = router,
            isVisibleFirstNote = notesListSharedState.value.isVisibleFirstNote,
            isScrollUp = notesListSharedState.value.isScrollUp,
            currentSelectedFolderId = model.currentSelectedFolderId
        )

        BottomBarLayer(
            state = model.bottomBarState,
            send = send,
            notesListSharedUiState = notesListSharedState,
        )

        searchBarApi.Widget()

        EditNoteFabLayer(
            editNoteFeatureApi = editNoteFeatureApi,
            isExpandedSearchBar = searchBarSharedUiState.value.state == SearchBarState.Expanded,
            isSelectionState = notesListSharedState.value.isSelectionState
        )

        AnimateFadeInOut(
            notesFoldersSharedState.value.isVisibleDialog,
            fadeInDuration = Theme.animSpeed.dialogVisibility,
            fadeOutDuration = Theme.animSpeed.dialogVisibility
        ) {
            notesFoldersFeatureApi.FolderCreationDialog()
        }
    }
}

@Composable
private fun NotesListLayer(
    send: SendMessage,
    chips: NoteFolderUi.Collection,
    notesList: NotesListApi.Ui,
    notesFolders: FoldersListApi.Ui,
    router: MainScreenRouter,
    isVisibleFirstNote: Boolean,
    isScrollUp: Boolean,
    currentSelectedFolderId: Long,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        val chipsOffset = animateDpAsState(
            if (isVisibleFirstNote) 0.dp
            else if (isScrollUp) 0.dp else -Theme.widgetSize.topBarNormalHeight, label = ""
        )

        Column {
            SystemStatusBar()
            notesList.ListWidget(router)
            SystemNavigationBar()
        }

        notesFolders.ChipsWidget(
            chips = chips,
            onChipClicked = { send(Msg.Ui.OnChipFilterClicked(it)) },
            currentSelectedChipId = currentSelectedFolderId,
            modifier = modifier
                .statusBarsPadding()
                .padding(top = Theme.widgetSize.topBarNormalHeight)
                .graphicsLayer {
                    translationY = chipsOffset.value.toPx()
                }
        )
    }
}

@Composable
private fun BottomBarLayer(
    state: MainBottomBarState,
    send: SendMessage,
    notesListSharedUiState: State<NotesListSharedUiState>,
) {
    MainBottomBar(state = state, send = send, notesListSharedUiState = notesListSharedUiState)
}

@Composable
private fun EditNoteFabLayer(
    editNoteFeatureApi: EditNoteApi.Ui,
    isExpandedSearchBar: Boolean,
    isSelectionState: Boolean
) {
    val fabTransition = animateFloatAsState(
        if (!isExpandedSearchBar.or(isSelectionState)) 1f else 0f, tween(Theme.animSpeed.common),
        label = ""
    )
    editNoteFeatureApi.ExpandableScreen(
        router = null,
        isEntryPoint = false,
        fabModifier = Modifier.graphicsLayer {
            scaleX = fabTransition.value
            scaleY = fabTransition.value
            alpha = fabTransition.value
        })
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: MainScreenRouter,
    foldersSharedUiState: SharedUiState<FoldersSharedUiState>,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateToAddNewNote -> router.toNoteEditor(0)
            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.ShowNoteForEdit -> router.toNoteEditor(eff.id)
            is Eff.NavigateToFoldersList -> router.toFoldersList()
            is Eff.UpdateFolderSelection -> {
                foldersSharedUiState.updateCurrentSelectedFolder(eff.currentSelectedId)
            }
        }
    }
}

@Composable
internal fun applyInitialChipTitleForLanguage(chip: NoteFolderUi): NoteFolderUi {
    val title = if (chip.id == 0L) text.folders.titlePinnedChipFilter else chip.title
    return chip.copy(title = title)
}