package ru.maksonic.beresta.feature.edit_note.core.ui.widget.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.NoteInputDefaultColors
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 07.03.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NoteTitleInputFieldWidget(
    title: String,
    send: SendMessage,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        send(Msg.Inner.ShowedKeyboardForExpandedFab)
    }

    TextField(
        value = title,
        onValueChange = { send(Msg.Inner.UpdatedCurrentNoteTitle(it)) },
        textStyle = TextDesign.topBar,
        placeholder = {
            Text(
                text.editNote.hintInputTitle,
                style = TextDesign.header.copy(color = outline)
            )
        },
        colors = NoteInputDefaultColors,
        maxLines = 500,
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(start = dp8, end = dp8)
            .verticalScroll(rememberScrollState(), false)
    )
}