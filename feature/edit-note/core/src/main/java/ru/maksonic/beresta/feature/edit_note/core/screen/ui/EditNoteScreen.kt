package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Model
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable
import ru.maksonic.beresta.ui.widget.toastLongTime

/**
 * @Author maksonic on 04.03.2023
 */
private const val KEYBOARD_DELAY = 500L

internal typealias SendMessage = (Msg) -> Unit

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    isExpandedFab: () -> Boolean = { false },
    collapseFabWidget: () -> Unit = {},
    isVisibleOnFabDraftIndicator: MutableState<Boolean> = mutableStateOf(false),
    router: EditNoteRouter? = null,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    sandbox.sendMsg(Msg.Inner.FetchedFabStateValue(isExpandedFab()))

    val model = sandbox.model.collectAsStateWithLifecycle().value

    HandleEffects(effects = sandbox.effects, router = router)
    Content(model, sandbox::sendMsg, collapseFabWidget, isVisibleOnFabDraftIndicator, modifier)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Content(
    model: Model,
    send: SendMessage,
    collapseFabWidget: () -> Unit,
    isVisibleOnFabDraftIndicator: MutableState<Boolean>,
    modifier: Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
            showDelayedKeyboard(titleFocus, keyboardController)
        }
    }

    BackHandler(model.isExpandedFabState) {
        focusManager.clearFocus()
        keyboardController?.hide()
        if (model.isExpandedFabState)
            collapseFabWidget()
    }

    Box(modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(background)
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(scrollState)
                .imePadding()
                .noRippleClickable { }
        ) {

            Spacer(modifier.height(Theme.widgetSize.topBarMediumHeight))

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

        TopActionButtonWithEditorPanelContainer(
            send = send,
            onFloatingTopButtonPressed = {
                keyboardController?.hide()
                focusManager.clearFocus()
                if (model.isExpandedFabState) {
                    collapseFabWidget()
                } else {
                    send(Msg.Ui.OnTopBarBackPressed)
                }
            },
            isVisibleEditorPanel = model.isVisibleEditorPanel,
            modifier = modifier
        )
    }
}

@Composable
private fun SystemBarsOverFlowContainer(isVisibleEditorPanel: Boolean, modifier: Modifier) {
    val navBarColor =
        animateColorAsState(if (isVisibleEditorPanel) tertiaryContainer else background)

    Column(modifier.fillMaxSize()) {
        val statusBarColor = background
        SystemStatusBar(backgroundColor = { statusBarColor })
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { navBarColor.value })
    }
}

@Composable
private fun TopActionButtonWithEditorPanelContainer(
    send: SendMessage,
    onFloatingTopButtonPressed: () -> Unit,
    isVisibleEditorPanel: Boolean,
    modifier: Modifier
) {
    Column(
        modifier
            .statusBarsPadding()
            .fillMaxSize()
            .imePadding()
    ) {
        IconActionCollapseEditFab(onBtnClicked = onFloatingTopButtonPressed)
        Spacer(modifier.weight(1f))
        EditNoteIdlePanelWidget(send, isScrollUp = { isVisibleEditorPanel })
    }
}

@Composable
private fun HandleEffects(effects: Flow<Eff>, router: EditNoteRouter?) {
    val context = LocalContext.current
    val noteMaxLengthWarning = text.editNote.noteMaxLengthWarning

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() } ?: {}
            is Eff.ShowSnackMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private suspend fun showDelayedKeyboard(
    titleFocus: FocusRequester,
    keyboardController: SoftwareKeyboardController?
) {
    titleFocus.requestFocus()
    keyboardController?.hide()
    delay(KEYBOARD_DELAY)
    keyboardController?.show()
}