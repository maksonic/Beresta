package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 07.03.2023
 */
@Composable
internal fun NoteMessageInputFieldWidget(
    message: String,
    send: Send,
    modifier: Modifier = Modifier,
) {
    val bottomPadding = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)


    TextField(
        value = message,
        onValueChange = { updatedField -> send(Msg.Inner.UpdatedCurrentNoteMessage(updatedField)) },
        textStyle = TextDesign.bodyLarge.copy(editorColors.textTint.value),
        placeholder = {
            Text(
                text.editor.hintInputMessage,
                style = TextDesign.bodyLarge.copy(editorColors.textTint.value.copy(0.7f))
            )
        },
        colors = NoteInputDefaultColors,
        keyboardActions = KeyboardActions.Default,
        keyboardOptions = KeyboardOptions.Default,
        maxLines = 1000,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding)
    )
}