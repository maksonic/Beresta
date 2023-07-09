package ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.edit_note.ui.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.NoteInputDefaultColors
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight

/**
 * @Author maksonic on 07.03.2023
 */
@Composable
internal fun NoteMessageInputFieldWidget(
    message: String,
    send: SendMessage,
    modifier: Modifier = Modifier,
) {
    val bottomPadding = Theme.widgetSize.bottomMainBarHeight.plus(SystemNavigationBarHeight)

    TextField(
        value = message,
        onValueChange = { updatedField -> send(Msg.Inner.UpdatedCurrentNoteMessage(updatedField)) },
        textStyle = TextDesign.bodyPrimary,
        placeholder = {
            Text(
                text.editNote.hintInputMessage,
                style = TextDesign.bodyPrimary.copy(color = outline)
            )
        },
        colors = NoteInputDefaultColors,
        keyboardActions = KeyboardActions.Default,
        keyboardOptions = KeyboardOptions.Default,
        maxLines = 500,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = dp8, end = dp8, bottom = bottomPadding)
    )
}