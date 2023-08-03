package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.ModalSheetBottomButtonsRow

/**
 * @Author maksonic on 16.07.2023
 */
@Composable
internal fun PinInformationContent(send: SendMessage, modifier: Modifier = Modifier) {
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

        ModalSheetBottomButtonsRow(
            leftTitle = text.shared.btnTitleCancel,
            rightTitle = text.shared.btnTitleAccept,
            onLeftClicked = { send(Msg.Ui.CloseDialog) },
            onRightClicked = { send(Msg.Ui.UpdateDialogContent(DialogContent.KEYBOARD)) }
        )
    }
}