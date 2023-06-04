package ru.maksonic.beresta.screen.trash_list.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.navigation.router.router.trash.TrashFoldersScreenRouter
import ru.maksonic.beresta.screen.trash_list.folders.core.Eff
import ru.maksonic.beresta.screen.trash_list.folders.core.FoldersTrashSandbox
import ru.maksonic.beresta.screen.trash_list.folders.core.Msg
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 30.05.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrashFoldersScreenContainer(
    router: TrashFoldersScreenRouter,
    foldersListApi: FoldersListApi.Ui = koinInject(),
    topBarCounterApi: TopBarCounterApi.Ui = koinInject(),
    dateFormatter: DateFormatter = koinInject(),
    sandbox: FoldersTrashSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    BackHandler(model.value.isSelectionState) {
        sandbox.send(Msg.Ui.CancelSelectionState)
    }

    BackHandler(model.value.modalBottomSheetState.isVisible) {
        sandbox.send(Msg.Ui.HideModalBottomSheet)
    }

    HandleUiEffects(
        effects = sandbox.effects,
        modalBottomSheetState = model.value.modalBottomSheetState,
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        router = router
    )

    TrashFoldersScreenContent(
        model = model,
        send = sandbox::send,
        foldersListApi = foldersListApi,
        topBarCounterApi = topBarCounterApi,
        formatter = dateFormatter
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit,
    router: TrashFoldersScreenRouter
) {
    val scope = rememberCoroutineScope()

    HandleEffectsWithLifecycle(effects) { eff ->
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