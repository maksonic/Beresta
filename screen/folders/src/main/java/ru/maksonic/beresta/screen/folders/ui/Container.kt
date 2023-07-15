package ru.maksonic.beresta.screen.folders.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState
import ru.maksonic.beresta.feature.folders_chips.api.ui.showForEdit
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter
import ru.maksonic.beresta.screen.folders.core.Eff
import ru.maksonic.beresta.screen.folders.core.FoldersScreenSandbox
import ru.maksonic.beresta.screen.folders.core.Msg

/**
 * @Author maksonic on 04.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: FoldersScreenRouter,
    foldersUiItemApi: FoldersApi.Ui.FolderItem = koinInject(),
    foldersPlaceholderApi: FoldersApi.Ui.Placeholder = koinInject(),
    sandbox: FoldersScreenSandbox = koinViewModel(),
    chipsDialogApi: FoldersApi.Ui.AddChipDialog = koinInject(),
    listSortUiState: SortingSheetApi.Ui = koinInject(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        sandbox.effects,
        router,
        chipsDialogApi.sharedUiState,
        modalBottomSheetState = model.value.modalSheet.state,
        hideSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
    )

    Content(
        foldersUiItemApi,
        foldersPlaceholderApi,
        chipsDialogApi,
        model,
        sandbox::send,
        listSortUiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: FoldersScreenRouter,
    addFolderDialogSharedUiState: SharedUiState<SharedNewFolderDialogUiState>,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.ShowFolderDialog -> addFolderDialogSharedUiState.showForEdit(eff.id)
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}