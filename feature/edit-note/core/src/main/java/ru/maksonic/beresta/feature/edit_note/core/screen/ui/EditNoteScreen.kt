package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Model
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 04.03.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    isExpandedFab: () -> Boolean = { false },
    collapseFabWidget: () -> Unit = {},
    router: EditNoteRouter? = null,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    BackHandler(isExpandedFab()) {
        focusRequester.freeFocus()
        keyboardController?.hide()
        if (isExpandedFab()) collapseFabWidget()

    }

    HandleEffects(effects = sandbox.effects, router = router)

    Content(
        model = model,
        send = sandbox::sendMsg,
        isExpandedFab = isExpandedFab,
        modifier = modifier,
        focusRequester = focusRequester,
        backAction = {
            keyboardController?.hide()
            focusRequester.freeFocus()
            if (isExpandedFab()) collapseFabWidget() else sandbox.sendMsg(Msg.Ui.OnTopBarBackPressed)
        }
    )
}

@Composable
fun Content(
    model: Model,
    send: SendMessage,
    isExpandedFab: () -> Boolean,
    modifier: Modifier,
    focusRequester: FocusRequester,
    backAction: () -> Unit,
) {
    val bg = background

    Column(
        modifier
            .fillMaxSize()
            .background(background)
            .noRippleClickable { }) {

        SystemStatusBar(backgroundColor = { bg })
        TopAppBarNormal(
            title = "",
            backgroundColor = { bg },
            backAction = backAction,
        )
        InputNoteTitle(
            inputValue = { model.inputTitle },
            updateTitle = { noteTitle -> send(Msg.Inner.UpdateInputTitle(noteTitle)) },
            isExpandedFab = isExpandedFab,
            focusRequester = focusRequester
        )
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { bg })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputNoteTitle(
    inputValue: () -> String,
    updateTitle: (text: String) -> Unit,
    modifier: Modifier = Modifier,
    isExpandedFab: () -> Boolean,
    focusRequester: FocusRequester,
) {

    SideEffect {
        if (isExpandedFab()) {
            focusRequester.requestFocus()
        }
    }

    TextField(
        value = inputValue(),
        onValueChange = { inputText -> updateTitle(inputText) },
        textStyle = TextDesign.header,
        placeholder = { Text("Заголовок", style = TextDesign.header.copy(color = secondary)) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = onPrimaryContainer,
            containerColor = transparent,
            cursorColor = primary,
            focusedIndicatorColor = transparent,
            unfocusedIndicatorColor = transparent,
            disabledIndicatorColor = transparent,
            selectionColors = TextSelectionColors(handleColor = primary, onSurfaceVariant),
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
            .focusRequester(focusRequester)

    )
}

@Composable
private fun HandleEffects(effects: Flow<Eff>, router: EditNoteRouter?) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() } ?: {}
        }
    }
}