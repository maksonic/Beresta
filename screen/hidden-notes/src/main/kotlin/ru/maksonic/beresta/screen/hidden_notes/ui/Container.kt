package ru.maksonic.beresta.screen.hidden_notes.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.animation.AnimateContent
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarDuration
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHostState
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarResult
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.LocalNoteCardState
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.LocalListHiddenNotesSortState
import ru.maksonic.beresta.feature.ui.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.ui.search_bar.api.isExpanded
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.routes.HiddenNotesRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.hidden_notes.core.Eff
import ru.maksonic.beresta.screen.hidden_notes.core.HiddenNotesSandbox
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.core.SnackBarKey

/**
 * @Author maksonic on 18.07.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: HiddenNotesRouter,
    sandbox: HiddenNotesSandbox = koinViewModel(),
    hiddenNotesPinInputDialogUiApi: HiddenNotesDialogUiApi.PinInputDialog = koinInject(),
    notesCardStateStoreUiApi: NotesCardUiApi.CardState = koinInject(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )
    val isUserAction = rememberSaveable { mutableStateOf(false) }

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        modalBottomSheetState = modalBottomSheetState,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        updateUserAction = { isUserAction.value = it },
        snackState = model.snackNotesState,
        send = sandbox::send
    )

    BackHandler {
        if (!model.isVisibleStonewall) {
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

    LaunchedEffect(model.editNoteFabState.state) {
        isUserAction.value = model.editNoteFabState.state.isExpanded
    }

    LaunchedEffect(model.searchBarState.barState) {
        isUserAction.value = model.searchBarState.barState.isExpanded
    }

    Box(Modifier.fillMaxSize()) {
        AnimateContent(model.isVisibleStonewall) {
            if (it) {
                val color = surface
                Canvas(modifier = Modifier.fillMaxSize(), onDraw = { drawRect(color) })

            } else {
                CompositionLocalProvider(
                    LocalListHiddenNotesSortState provides model.sortState,
                    LocalNoteCardState provides notesCardStateStoreUiApi.sharedState.value,
                ) {
                    Content(model, sandbox::send, modalBottomSheetState)
                }
            }
        }

        hiddenNotesPinInputDialogUiApi.Widget(
            isVisible = model.isVisibleStonewall,
            onSuccessPin = { sandbox.send(Msg.Inner.UpdateStonewallVisibility(false)) },
            hideDialog = { },
            isBlocked = model.isVisibleStonewall,
            onBlockedBackPressed = { sandbox.send(Msg.Ui.OnTopBarBackPressed) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: HiddenNotesRouter,
    modalBottomSheetState: SheetState,
    hideModalSheet: () -> Unit,
    updateUserAction: (Boolean) -> Unit,
    snackState: SnackBarHostState,
    send: Send
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
                    when (eff.key) {
                        SnackBarKey.REMOVED_NOTES -> {
                            val result = snackState.showSnackBar(
                                message = "$removedNotesPrefix ${eff.message}",
                                actionLabel = eff.key.id.toString(),
                                duration = SnackBarDuration.Normal
                            )

                            if (result == SnackBarResult.ActionPerformed) {
                                send(Msg.Ui.OnSnackUndoRemoveNotesClicked)
                            }
                        }
                    }
                }
            }
        }
    }
}