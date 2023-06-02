package ru.maksonic.beresta.feature.notes.folders.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.showDialog
import ru.maksonic.beresta.feature.notes.folders.api.ui.updateCurrentSelectedFolder
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Eff
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Model
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Msg
import ru.maksonic.beresta.feature.notes.folders.core.screen.ui.widget.FoldersLoaderWidget
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListSharedUiState
import ru.maksonic.beresta.feature.notes.list.api.ui.updatePassedList
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.NotesFoldersScreenRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 15.05.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotesFoldersScreenContent(
    model: Model,
    send: SendMessage,
    notesFoldersFeatureApi: FoldersListApi.Ui,
    notesListApi: NotesListApi.Ui,
    topBarCounterApi: TopBarCounterApi.Ui,
    modifier: Modifier = Modifier
) {
    val sharedUiState = notesFoldersFeatureApi.sharedUiState.state.collectAsStateWithLifecycle()
    val notesListSharedUiState = notesListApi.sharedUiState.state.collectAsStateWithLifecycle()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    BackHandler(model.isSelectionState) {
        send(Msg.Ui.CancelSelectionState)
    }

    LaunchedEffect(Unit) {
        if (model.isMoveNotesToFolder)
            send(
                Msg.Inner.FetchedPassedReplaceNotesState(
                    notesListSharedUiState.value.passedToFolderNotes
                )
            )
    }

    Box(modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                val title = with(text.folders) {
                    if (model.isMoveNotesToFolder) btnTitleSelectFolder else topBarTitle
                }

                topBarCounterApi.LargeWidget(
                    scrollBehavior = scrollBehavior,
                    isSelectionState = model.isSelectionState,
                    count = model.selectedFolders.count(),
                    idleTitle = title,
                    onBackClicked = { send(Msg.Ui.OnTopBarBackPressed) },
                    onCancelSelectStateClicked = { send(Msg.Ui.CancelSelectionState) },
                    onSelectAllItemsClicked = { send(Msg.Ui.OnSelectAllFoldersClicked) })
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->

            FetchedDataWidget(
                model = model,
                send = send,
                notesFoldersFeatureApi = notesFoldersFeatureApi,
                modifier = modifier.padding(paddings)
            )
        }


        AnimateFadeInOut(
            sharedUiState.value.isVisibleDialog,
            fadeInDuration = Theme.animSpeed.dialogVisibility,
            fadeOutDuration = Theme.animSpeed.dialogVisibility
        ) {
            notesFoldersFeatureApi.FolderCreationDialog()
        }
    }
}

@Composable
private fun FetchedDataWidget(
    model: Model,
    send: SendMessage,
    notesFoldersFeatureApi: FoldersListApi.Ui,
    modifier: Modifier
) {
    val scrollState = rememberLazyListState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        AnimateContent(model.base.isLoading) { isLoading ->
            if (isLoading) {
                FoldersLoaderWidget(modifier)
            } else {
                FoldersListContent(
                    folders = notesFoldersFeatureApi.applyStickyItemsTitle(model.folders),
                    model = model,
                    send = send,
                    scrollState = scrollState,
                    modifier = modifier
                )
                BottomBarContent(
                    model = model,
                    send = send,
                    scrollState = scrollState,
                    isDisabledBottomBar = model.selectedFolders.isEmpty() && model.isSelectionState,
                )
            }
        }
    }
}

@Composable
internal fun HandleUiEffects(
    effects: Flow<Eff>,
    router: NotesFoldersScreenRouter,
    sharedUiState: SharedUiState<FoldersSharedUiState>,
    notesListSharedState: SharedUiState<NotesListSharedUiState>,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.ShowFolderDialog -> sharedUiState.showDialog(eff.isNewFolder, eff.id)
            is Eff.ClearPassedForReplaceNotes -> notesListSharedState.updatePassedList(emptyList())
            is Eff.UpdateCurrentSelectedFolderInSharedState -> {
                sharedUiState.updateCurrentSelectedFolder(eff.id)
            }
        }
    }
}