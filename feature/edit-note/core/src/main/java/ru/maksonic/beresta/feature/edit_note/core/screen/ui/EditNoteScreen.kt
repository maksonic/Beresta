package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Model
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.*
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable
import ru.maksonic.beresta.ui.widget.toastLongTime

/**
 * @Author maksonic on 04.03.2023
 */
private const val KEYBOARD_DELAY = 500L

internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    isExpandedFab: () -> Boolean = { false },
    isFullExpandedFab: () -> Boolean = { false },
    collapseFabWidget: () -> Unit = {},
    isVisibleOnFabDraftIndicator: MutableState<Boolean> = mutableStateOf(false),
    router: EditNoteRouter? = null,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    sandbox.sendMsg(Msg.Inner.FetchedFabStateValue(isExpandedFab()))

    val model = sandbox.model.collectAsStateWithLifecycle().value

    HandleEffects(
        sandbox.effects,
        router,
        collapseFabWidget,
        modalSheetState = model.modalBottomSheetState
    )

    Content(
        model = model,
        send = sandbox::sendMsg,
        isVisibleOnFabDraftIndicator = isVisibleOnFabDraftIndicator,
        isFullExpandedFab = isFullExpandedFab,
        modifier = modifier
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun Content(
    model: Model,
    send: SendMessage,
    isVisibleOnFabDraftIndicator: MutableState<Boolean>,
    isFullExpandedFab: () -> Boolean,
    wallpaperSelectorApi: NoteWallpaperSelectorApi = get(),
    modifier: Modifier,
) {
    val focusManager = LocalFocusManager.current
    val (titleFocus, messageFocus) = remember { FocusRequester.createRefs() }
    val scrollState = rememberScrollState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                send(Msg.Inner.UpdatedEditorPanelVisibility(isVisible = available.y > 0f))
                return Offset.Zero
            }
        }
    }

    SideEffect {
        isVisibleOnFabDraftIndicator.value =
            model.titleField.text.isNotBlank() || model.messageField.text.isNotBlank()
    }

    LaunchedEffect(Unit) {
        if (model.isExpandedFabState) {
            showDelayedKeyboard(send, titleFocus)
        }
    }

    BackHandler(model.isExpandedFabState) {
        focusManager.clearFocus()
        send(Msg.Ui.OnTopBarBackPressed)
    }

    ModalBottomSheetLayout(
        sheetState = model.modalBottomSheetState,
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.Level0,
        sheetContent = {
            MultipleModalBottomSheetContent(
                send = send,
                currentSheetContent = model.currentSheetContent,
                modalSheetState = { model.modalBottomSheetState },
                isFullExpandedFab = isFullExpandedFab,
                wallpaperSelector = wallpaperSelectorApi,
                modifier = modifier
            )
        },
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier
                .fillMaxSize()
                .background(background)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
                    .verticalScroll(scrollState)
                    .imePadding()
                    .noRippleClickable { }
            ) {

                Spacer(
                    modifier
                        .height(Theme.widgetSize.topBarNormalHeight)
                        .statusBarsPadding()
                )

                NoteTitleInputFieldWidget(
                    inputValue = model.titleField,
                    updateTitle = { titleField -> send(Msg.Inner.UpdatedInputTitle(titleField)) },
                    focusRequester = titleFocus,
                    focusManager = focusManager,
                )
                NoteMessageInputFieldWidget(
                    inputValue = model.messageField,
                    updateMessage = { msgField -> send(Msg.Inner.UpdatedInputMessage(msgField)) },
                    focusRequester = messageFocus,
                )
            }

            SystemBarsOverFlowContainer(isVisibleEditorPanel = model.isVisibleEditorPanel, modifier)

            TopBarWithEditorPanelContainer(
                send = send,
                editorPanelState = model.editorPanelState,
                onTopBarBackPressed = { send(Msg.Ui.OnTopBarBackPressed) },
                isVisibleEditorPanel = model.isVisibleEditorPanel,
                modifier = modifier
            )
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
private fun HandleEffects(
    effects: Flow<Eff>,
    router: EditNoteRouter?,
    collapseFabWidget: () -> Unit,
    modalSheetState: ModalBottomSheetState,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val noteMaxLengthWarning = text.editNote.noteMaxLengthWarning

    HandleEffectsWithLifecycle(effects) { eff ->

        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() } ?: {}
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning)
            is Eff.HideSystemKeyboard -> keyboardController?.hide()
            is Eff.ShowSystemKeyboard -> keyboardController?.show()
            is Eff.CollapseFab -> collapseFabWidget()
            is Eff.HideModalSheet -> {
                scope.launch {
                    modalSheetState.animateTo(ModalBottomSheetValue.Hidden)
                }
            }
            is Eff.ShowModalSheet -> {
                scope.launch {
                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }
    }
}

private suspend fun showDelayedKeyboard(send: SendMessage, titleFocus: FocusRequester) {
    titleFocus.requestFocus()
    send(Msg.Inner.HideKeyboard)
    delay(KEYBOARD_DELAY)
    send(Msg.Inner.ShowKeyboard)
}