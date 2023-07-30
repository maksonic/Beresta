package ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.feature.edit_note.ui.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.NoteInputDefaultColors
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 07.03.2023
 */
@Composable
internal fun NoteTitleInputFieldWidget(
    title: String,
    send: SendMessage,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

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
            .padding(start = dp8, end = dp8, top = Theme.widgetSize.topBarNormalHeight)
    )
}