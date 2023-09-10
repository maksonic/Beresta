package ru.maksonic.beresta.screen.main.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.screen.main.core.Eff
import ru.maksonic.beresta.screen.main.core.MainSandbox
import ru.maksonic.beresta.screen.main.core.MainSnackBarKey
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 22.06.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: MainScreenRouter,
    sandbox: MainSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        modalBottomSheetState = model.value.modalSheet.state,
        snackState = model.value.snackNotesState,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        send = sandbox::send
    )

    Content(model, sandbox::send)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: MainScreenRouter,
    modalBottomSheetState: SheetState,
    snackState: SnackbarHostState,
    hideModalSheet: () -> Unit,
    send: SendMessage,
    chipsDialogApi: FoldersApi.AddChipDialog.Ui = koinInject()
) {
    val scope = rememberCoroutineScope()
    val snackScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    // Snack messages
    val removedNotesPrefix = text.shared.hintRemovedNotesCount

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideModalSheet()
                    }
                }
            }

            is Eff.NavigateToEditNote -> {
                focusManager.clearFocus().let { router.toNoteEditor(eff.id) }
            }

            is Eff.NavigateToSettings -> router.toSettings()
            is Eff.NavigateToFolders -> router.toFoldersList(eff.ids)
            is Eff.NavigateToTrash -> router.toTrash()
            is Eff.NavigateToHiddenNotes -> router.toHiddenNotes()
            is Eff.ShowAddNewChipDialog -> chipsDialogApi.addFolder()

            is Eff.ShowSnackBar -> {
                snackScope.launch {
                    when (eff.key) {
                        MainSnackBarKey.REMOVED_NOTES -> {
                            val result = snackState.showSnackbar(
                                message = "$removedNotesPrefix ${eff.message}",
                                actionLabel = eff.key.name,
                                duration = SnackbarDuration.Short
                            )

                            if (result == SnackbarResult.ActionPerformed) {
                                send(Msg.Ui.OnSnackUndoRemoveNotesClicked)
                            }
                        }
                    }
                }
            }
        }
    }
}