package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.widget.ModalSheetBottomButtonsRow
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.DialogContent
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 16.07.2023
 */
@Composable
internal fun PinInformationContent(send: Send, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text.hiddenNotes.dialogTitle,
            style = TextDesign.headlineSmall,
            modifier = modifier.padding(top = dp24, bottom = dp8)
        )
        Text(
            text.hiddenNotes.dialogMessage,
            style = TextDesign.bodyMedium,
            modifier = modifier.padding(start = dp16, end = dp16, top = dp16)
        )

        ModalSheetBottomButtonsRow(
            leftTitle = text.shared.btnTitleCancel,
            rightTitle = text.shared.btnTitleAccept,
            onLeftClicked = { send(Msg.Ui.ClosedDialog) },
            onRightClicked = { send(Msg.Ui.UpdateDialogContent(DialogContent.KEYBOARD)) }
        )
    }
}