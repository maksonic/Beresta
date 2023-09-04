package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
 * @Author maksonic on 03.08.2023
 */

@Composable
fun ResetPinCodeContent(
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text.hiddenNotes.dialogTitleResetPin,
            style = TextDesign.topBar,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(dp16, dp24, dp16, dp16)
        )
        Text(
            text.hiddenNotes.dialogMessageResetPin,
            style = TextDesign.bodyPrimary,
            modifier = modifier.padding(start = dp16, end = dp16, bottom = dp8)
        )

        ModalSheetBottomButtonsRow(
            leftTitle = text.shared.btnTitleCancel,
            rightTitle = text.shared.btnTitleCreate,
            onLeftClicked = { send(Msg.Ui.UpdateDialogContent(DialogContent.KEYBOARD)) },
            onRightClicked = { send(Msg.Ui.OnResetPinClicked) }
        )
    }
}