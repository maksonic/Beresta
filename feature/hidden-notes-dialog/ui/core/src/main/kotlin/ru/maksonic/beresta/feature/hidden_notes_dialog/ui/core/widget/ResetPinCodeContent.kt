package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.beresta.common.ui_kit.widget.ModalSheetBottomButtonsRow
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 03.08.2023
 */

@Composable
fun ResetPinCodeContent(
    send: Send,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text.hiddenNotes.dialogTitleResetPin,
            style = TextDesign.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth().padding(dp16, dp24, dp16, dp16)
        )
        Text(
            text.hiddenNotes.dialogMessageResetPin,
            style = TextDesign.bodyMedium,
            modifier = modifier.padding(start = dp16, end = dp16)
        )

        ModalSheetBottomButtonsRow(
            leftTitle = text.shared.btnTitleCancel,
            rightTitle = text.shared.btnTitleCreate,
            onLeftClicked = { send(Msg.Ui.UpdateDialogContent(DialogContent.KEYBOARD)) },
            onRightClicked = { send(Msg.Ui.OnResetPinClicked) }
        )
    }
}