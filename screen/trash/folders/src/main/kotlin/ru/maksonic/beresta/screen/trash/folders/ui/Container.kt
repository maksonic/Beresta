package ru.maksonic.beresta.screen.trash.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersListUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.FoldersSortUi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListFoldersSortState
import ru.maksonic.beresta.navigation.router.routes.trash.TrashFoldersRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.trash.folders.core.Eff
import ru.maksonic.beresta.screen.trash.folders.core.FoldersTrashSandbox
import ru.maksonic.beresta.screen.trash.folders.core.Msg

/**
 * @Author maksonic on 30.05.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: TrashFoldersRouter,
    foldersListApi: FoldersListUiApi = koinInject(),
    sandbox: FoldersTrashSandbox = koinViewModel(),
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    BackHandler(model.isSelection) {
        sandbox.send(Msg.Ui.CancelSelectionState)
    }

    HandleUiEffects(
        effects = sandbox.effects,
        modalBottomSheetState = modalBottomSheetState,
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        router = router
    )

    CompositionLocalProvider(
        LocalListFoldersSortState provides FoldersSortUi.Default,
        LocalCurrentSelectedFolderState provides 0
    ) {
        Content(model, sandbox::send, foldersListApi, modalBottomSheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit,
    router: TrashFoldersRouter
) {
    val scope = rememberCoroutineScope()

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
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