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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.showDialog
import ru.maksonic.beresta.feature.notes.folders.api.ui.updateCurrentSelectedFolder
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Eff
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Model
import ru.maksonic.beresta.feature.notes.folders.core.screen.core.Msg
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.NotesFoldersScreenRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
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
    topBarCounterApi: TopBarCounterApi.Ui,
    modifier: Modifier = Modifier
) {
    val sharedUiState = notesFoldersFeatureApi.sharedUiState.state.collectAsState()
    val scrollState = rememberLazyListState()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    BackHandler(model.isSelectionState) {
        send(Msg.Ui.CancelSelectionState)
    }

    LaunchedEffect(sharedUiState.value.currentFolderId) {
        send(Msg.Inner.UpdateCurrentSelectedFolder(sharedUiState.value.currentFolderId))
    }

    Box(modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                topBarCounterApi.LargeWidget(
                    scrollBehavior = scrollBehavior,
                    isSelectionState = model.isSelectionState,
                    count = model.selectedFolders.count(),
                    idleTitle = text.folders.topBarTitle,
                    onBackClicked = { send(Msg.Ui.OnTopBarBackPressed) },
                    onCancelSelectStateClicked = { send(Msg.Ui.CancelSelectionState) },
                    onSelectAllItemsClicked = { send(Msg.Ui.OnSelectAllFoldersClicked) })
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            FoldersListContent(
                folders = notesFoldersFeatureApi.applyStickyItemsTitle(model.folders),
                model = model,
                send = send,
                scrollState = scrollState,
                modifier = Modifier.padding(paddingValues = paddings)
            )

            BottomBarContent(model, send, scrollState)
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
internal fun HandleUiEffects(
    effects: Flow<Eff>,
    router: NotesFoldersScreenRouter,
    sharedUiState: SharedUiState<FoldersSharedUiState>,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.ShowFolderDialog -> sharedUiState.showDialog(eff.isNewFolder, eff.id)
            is Eff.UpdateCurrentSelectedFolderInSharedState -> {
                sharedUiState.updateCurrentSelectedFolder(eff.id)
            }
        }
    }
}