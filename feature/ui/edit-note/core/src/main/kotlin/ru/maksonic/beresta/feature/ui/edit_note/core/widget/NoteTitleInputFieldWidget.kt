package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 07.03.2023
 */
@Composable
internal fun NoteTitleInputFieldWidget(
    title: String,
    send: Send,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = title,
        onValueChange = { send(Msg.Inner.UpdatedCurrentNoteTitle(it)) },
        textStyle = TextDesign.titleLarge.copy(editorColors.textTint.value),
        placeholder = {
            Text(
                text.editor.hintInputTitle,
                style = TextDesign.titleLarge.copy(editorColors.textTint.value.copy(0.7f))
            )
        },
        colors = NoteInputDefaultColors,
        maxLines = 1000,
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
    )
}

internal val NoteInputDefaultColors
    @Composable get() = TextFieldDefaults.colors(
        focusedTextColor = onBackground,
        disabledTextColor = onBackground,
        focusedContainerColor = transparent,
        cursorColor = primary,
        focusedIndicatorColor = transparent,
        unfocusedIndicatorColor = transparent,
        disabledIndicatorColor = transparent,
        unfocusedContainerColor = transparent,
        selectionColors = TextSelectionColors(handleColor = primary, onSurfaceVariant),
    )