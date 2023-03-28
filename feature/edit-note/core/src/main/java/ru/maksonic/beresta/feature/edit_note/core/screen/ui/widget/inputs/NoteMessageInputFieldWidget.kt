package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.ui.theme.color.NoteInputDefaultColors
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 07.03.2023
 */
@Composable
internal fun NoteMessageInputFieldWidget(
    message: String,
    updateMessage: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {

    TextField(
        value = message,
        onValueChange = { inputText -> updateMessage(inputText) },
        textStyle = TextDesign.bodyPrimary,
        placeholder = {
            Text(
                text.editNote.hintInputMessage, style = TextDesign.bodyPrimary.copy(color = outline)
            )
        },
        colors = NoteInputDefaultColors,
        maxLines = 500,
        minLines = 10,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(start = dp8, end = dp8)
            .verticalScroll(rememberScrollState(), false)
            .focusRequester(focusRequester)
            .imePadding()
    )
}