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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.job
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Model
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.NoteTitleInputFieldWidget
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 04.03.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    isExpandedFab: () -> Boolean = { false },
    collapseFabWidget: () -> Unit = {},
    router: EditNoteRouter? = null,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    sandbox.sendMsg(Msg.Inner.FetchedFabStateValue(isExpandedFab()))
    val model = sandbox.model.collectAsStateWithLifecycle().value

    HandleEffects(effects = sandbox.effects, router = router)

    Content(model, sandbox::sendMsg, collapseFabWidget, modifier)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Content(
    model: Model,
    send: SendMessage,
    collapseFabWidget: () -> Unit,
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

    LaunchedEffect(Unit) {
        if (model.isExpandedFabState) {
            this.coroutineContext.job.invokeOnCompletion {
                keyboardController?.show()
                titleFocus.requestFocus()
            }
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
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() } ?: {}
        }
    }
}