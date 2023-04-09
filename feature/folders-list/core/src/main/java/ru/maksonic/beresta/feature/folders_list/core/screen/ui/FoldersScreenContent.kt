package ru.maksonic.beresta.feature.folders_list.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.updateDialogVisibility
import ru.maksonic.beresta.feature.folders_list.core.applyInitialChipTitleForLanguage
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Eff
import ru.maksonic.beresta.feature.folders_list.core.screen.core.FoldersScreenSandbox
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Msg
import ru.maksonic.beresta.feature.folders_list.core.screen.ui.widget.BottomBarSelectionContainer
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.bar.TopAppBarCollapsingLarge
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 03.04.2023
 */

internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FoldersScreenContent(
    modifier: Modifier = Modifier,
    api: FoldersListApi.Ui = get(),
    sandbox: FoldersScreenSandbox = koinViewModel(),
    panelCounter: SelectedItemsPanelUiApi = get(),
    router: FoldersScreenRouter,
) {
    val model = sandbox.model.collectAsStateWithLifecycle().value
    val sharedUiState = api.sharedUiState.state.collectAsState().value

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        sharedUiState = api.sharedUiState
    )

    LaunchedEffect(sharedUiState.currentFolderId) {
        sandbox.send(Msg.Inner.UpdateCurrentSelectedFolder(sharedUiState.currentFolderId))
    }

    LaunchedEffect(sharedUiState.isVisibleDialog) {
        sandbox.send(Msg.Inner.UpdatedNewFolderDialogVisibility(sharedUiState.isVisibleDialog))
    }

    BackHandler(model.isSelectionState) {
        sandbox.send(Msg.Ui.OnCancelSelectionClicked)
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        val scrollState = rememberLazyListState()
        val scrollBehavior =
            TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                TopAppBarCollapsingLarge(
                    scrollBehavior = scrollBehavior,
                    title = text.folders.topBarTitle,
                    onBackAction = { sandbox.send(Msg.Ui.OnTopBarBackPressed) }
                )
            },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            when {
                model.base.isLoading -> LoadingViewState()
                model.folders.data.isEmpty() -> FetchedEmptyListContent()
                model.folders.data.isNotEmpty() -> {
                    FetchedSuccessListContent(
                        model = model,
                        send = sandbox::send,
                        folders = model.folders.copy(
                            data = model.folders.data.map {
                                applyInitialChipTitleForLanguage(chip = it)
                            }
                        ),
                        scrollState = { scrollState },
                        modifier.padding(paddingValues = paddings)
                    )
                }
            }
        }

        BottomBarSelectionContainer(
            send = sandbox::send,
            panelCounterApi = panelCounter,
            selectedCount = { model.selectedFoldersCount },
            isShowUnpin = model.isSelectionState,
            isSelectionState = model.isSelectionState,
            isScrolledToBottom = { !scrollState.canScrollForward },
        )

        AnimateFadeInOut(model.isVisibleNewFolderDialog) {
            api.FolderCreationDialog()
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: FoldersScreenRouter,
    sharedUiState: SharedUiState<FoldersSharedUiState>,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.UpdateFolderSelection -> {
                sharedUiState.updateState { state -> state.copy(currentFolderId = eff.id) }
            }
            is Eff.UpdatePassedEditableFolderId -> {
                sharedUiState.updateState { state -> state.copy(passedForEditFolderId = eff.id) }
            }
            is Eff.ShowFolderDialog -> sharedUiState.updateDialogVisibility(true)
        }
    }
}
