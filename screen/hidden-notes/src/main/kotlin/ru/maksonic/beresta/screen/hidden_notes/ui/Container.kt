package ru.maksonic.beresta.screen.hidden_notes.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.HiddenNotesScreenRouter
import ru.maksonic.beresta.screen.hidden_notes.core.Eff
import ru.maksonic.beresta.screen.hidden_notes.core.HiddenNotesSandbox
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.widget.HiddenNotesDialog
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent

/**
 * @Author maksonic on 18.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: HiddenNotesScreenRouter,
    sandbox: HiddenNotesSandbox = koinViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val isUserAction = rememberSaveable { mutableStateOf(false) }

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        modalBottomSheetState = model.value.modalSheet.state,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        updateUserAction = { isUserAction.value = it },
        snackState = model.value.snackNotesState,
        send = sandbox::send
    )

    BackHandler {
        if (!model.value.isVisibleStonewall) {
            sandbox.send(Msg.Ui.OnTopBarBackPressed)
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP && !isUserAction.value) {
                sandbox.send(Msg.Inner.UpdateStonewallVisibility(true))
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(model.value.editNoteFabState.state) {
        isUserAction.value = model.value.editNoteFabState.state.isExpanded
    }

    LaunchedEffect(model.value.searchBarState.barState) {
        isUserAction.value = model.value.searchBarState.barState.isExpanded
    }

    Box(Modifier.fillMaxSize()) {
        AnimateContent(model.value.isVisibleStonewall) {
            if (it) {
                val color = surface
                Canvas(modifier = Modifier.fillMaxSize(), onDraw = { drawRect(color) })

            } else {
                Content(model, sandbox::send)
            }
        }

        HiddenNotesDialog(isVisible = model.value.isVisibleStonewall, sandbox::send)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: HiddenNotesScreenRouter,
    modalBottomSheetState: SheetState,
    hideModalSheet: () -> Unit,
    updateUserAction: (Boolean) -> Unit,
    snackState: SnackbarHostState,
    send: SendMessage
) {
    val scope = rememberCoroutineScope()
    val snackScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    // Snack messages
    val removedNotesPrefix = text.shared.hintRemovedNotesCount

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideModalSheet()
                    }
                }
            }

            is Eff.NavigateToEditNote -> {
                focusManager.clearFocus().let {
                    updateUserAction(true)
                    router.toNoteEditor(eff.id)
                }
            }

            is Eff.ShowSnackBar -> {
                snackScope.launch {
                    val result = snackState.showSnackbar(
                        message = "$removedNotesPrefix ${eff.message}",
                        actionLabel = "Undo remove selected notes",
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