package ru.maksonic.beresta.screen.trash_list.folders.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
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

@Composable
internal fun TrashFoldersScreenContainer(
    router: TrashFoldersScreenRouter,
    topBarCounterApi: TopBarCounterApi.Ui = get(),
    sandbox: FoldersTrashSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    BackHandler(model.value.isSelectionState) {
        sandbox.send(Msg.Ui.CancelSelectionState)
    }

    HandleUiEffects(effects = sandbox.effects, router = router)

    TrashFoldersScreenContent(
        model = model,
        send = sandbox::send,
        topBarCounterApi = topBarCounterApi,
    )
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: TrashFoldersScreenRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.NavigateToTrashedFoldersList -> {}
        }
    }
}