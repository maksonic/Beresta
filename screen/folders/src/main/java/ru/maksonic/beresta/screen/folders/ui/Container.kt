package ru.maksonic.beresta.screen.folders.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState
import ru.maksonic.beresta.feature.folders_chips.api.ui.showForEdit
import ru.maksonic.beresta.screen.folders.core.Eff
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter
import ru.maksonic.beresta.screen.folders.core.FoldersScreenSandbox

/**
 * @Author maksonic on 04.07.2023
 */
@Composable
internal fun Container(
    router: FoldersScreenRouter,
    foldersUiItemApi: FoldersApi.Ui.FolderItem = koinInject(),
    sandbox: FoldersScreenSandbox = koinViewModel(),
    chipsRowApi: FoldersApi.Ui.ChipsRow = koinInject(),
    chipsDialogApi: FoldersApi.Ui.AddChipDialog = koinInject()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        sandbox.effects,
        router,
        chipsRowApi.currentSelectedChipId,
        chipsDialogApi.sharedUiState
    )

    Content(foldersUiItemApi, chipsDialogApi, model, sandbox::send)
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: FoldersScreenRouter,
    currentFolderState: MutableState<Long>,
    addFolderDialogSharedUiState: SharedUiState<SharedNewFolderDialogUiState>
) {
    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.ShowFolderDialog -> addFolderDialogSharedUiState.showForEdit(eff.id)
            is Eff.UpdateCurrentSelectedFolderInSharedState -> currentFolderState.value = eff.id
        }
    }
}