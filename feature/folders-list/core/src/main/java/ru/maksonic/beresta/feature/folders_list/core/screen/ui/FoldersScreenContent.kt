package ru.maksonic.beresta.feature.folders_list.core.screen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.folders_list.core.applyInitialChipTitleForLanguage
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Eff
import ru.maksonic.beresta.feature.folders_list.core.screen.core.FoldersScreenSandbox
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Msg
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.LoadingViewState
import ru.maksonic.beresta.ui.widget.bar.TopAppBarCollapsingLarge
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
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
    router: FoldersScreenRouter,
) {
    val model = sandbox.model.collectAsStateWithLifecycle().value
    val sharedUiState = api.sharedUiState.state.collectAsState().value

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        updateFolderSelection = { id ->
            api.sharedUiState.updateState { old -> old.copy(currentFolderId = id) }
        }
    )

    LaunchedEffect(sharedUiState.currentFolderId) {
        sandbox.send(Msg.Inner.UpdateCurrentSelectedFolder(sharedUiState.currentFolderId))
    }

    LaunchedEffect(sharedUiState.isVisibleDialog) {
        sandbox.send(Msg.Inner.UpdatedNewFolderDialogVisibility(sharedUiState.isVisibleDialog))
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

        PrimaryButton(
            action = { api.sharedUiState.updateState { old -> old.copy(isVisibleDialog = true) } },
            title = text.folders.btnTitleCreateNewFolder,
            elevation = ButtonDefaults.elevation(defaultElevation = Theme.elevation.Level3),
            modifier = modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(start = dp16, end = dp16, bottom = dp16)
        )

        AnimateFadeInOut(model.isVisibleNewFolderDialog) {
            api.FolderCreationDialog()
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>, router: FoldersScreenRouter, updateFolderSelection: (id: Long) -> Unit,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.UpdateFolderSelection -> updateFolderSelection(eff.id)
        }
    }
}
