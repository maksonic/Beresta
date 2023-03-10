package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.ui.theme.color.NoteInputDefaultColors
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 07.03.2023
 */
@Composable
internal fun NoteTitleInputFieldWidget(
    inputValue: TextFieldValue,
    updateTitle: (TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = inputValue,
        onValueChange = { inputText -> updateTitle(inputText) },
        textStyle = TextDesign.topBar,
        placeholder = {
            Text(
                text.editNote.hintInputTitle,
                style = TextDesign.header.copy(color = secondary)
            )
        },
        colors = NoteInputDefaultColors,
        maxLines = 500,
        keyboardActions = KeyboardActions {
            focusManager.moveFocus(FocusDirection.Next)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(start = dp8, end = dp8)
            .focusRequester(focusRequester)
            .verticalScroll(rememberScrollState(), false)
    )
}