package ru.maksonic.beresta.screen.folders.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter
import ru.maksonic.beresta.screen.folders.core.Eff
import ru.maksonic.beresta.screen.folders.core.FoldersScreenSandbox
import ru.maksonic.beresta.screen.folders.core.Msg

/**
 * @Author maksonic on 04.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: FoldersScreenRouter,
    sandbox: FoldersScreenSandbox = koinViewModel(),
    chipsDialogApi: FoldersApi.AddChipDialog.Ui = koinInject()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        send = sandbox::send,
        chipsDialogApi = chipsDialogApi,
        modalBottomSheetState = model.value.modalSheet.state,
        snackState = model.value.snackState,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) }
    )

    Content(model, sandbox::send, chipsDialogApi)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: FoldersScreenRouter,
    send: SendMessage,
    chipsDialogApi: FoldersApi.AddChipDialog.Ui,
    modalBottomSheetState: SheetState,
    snackState: SnackbarHostState,
    hideModalSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackScope = rememberCoroutineScope()
    // Snack messages
    val removedFoldersPrefix = text.folders.hintRemovedFoldersCount

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.AddNewFolder -> chipsDialogApi.addFolder()
            is Eff.UpdateFolder -> chipsDialogApi.updateFolder(eff.id)
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideModalSheet()
                    }
                }
            }

            is Eff.NavigateToHiddenNotes -> router.toHiddenNotes()

            is Eff.ShowSnackBar -> {
                snackScope.launch {
                    val result = snackState.showSnackbar(
                        message = "$removedFoldersPrefix ${eff.message}",
                        actionLabel = "Undo remove selected folders",
                        duration = SnackbarDuration.Short
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        send(Msg.Ui.OnSnackUndoRemoveFoldersClicked)
                    }
                }
            }
        }
    }
}