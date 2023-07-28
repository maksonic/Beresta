package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogCurrentContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.DialogButton

/**
 * @Author maksonic on 16.07.2023
 */
@Composable
internal fun HiddenNotesInformation(send: SendMessage, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text.hiddenNotes.dialogTitle,
            style = TextDesign.topBar,
            modifier = modifier.padding(top = dp24, bottom = dp8)
        )
        Text(
            text.hiddenNotes.dialogMessage,
            style = TextDesign.bodyPrimary, modifier = modifier.padding(dp16)
        )

        Row(
            modifier.padding(dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16)
        ) {

            DialogButton(
                action = { send(Msg.Ui.CloseDialog) },
                title = text.shared.btnTitleCancel,
                isDismiss = true,
                modifier = Modifier.weight(1f)
            )
            DialogButton(
                action = { send(Msg.Ui.UpdateDialogCurrentContent(DialogCurrentContent.KEYBOARD)) },
                title = text.shared.btnTitleAccept,
                isDismiss = false,
                modifier = Modifier.weight(1f)
            )
        }
    }
}