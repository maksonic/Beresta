package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ru.maksonic.beresta.common.ui_kit.dialog.DialogBase
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 11.10.2023
 */
class NotCreatedHiddenNotesPinDialog : HiddenNotesDialogUiApi.NotCreatedPinDialog {
    @Composable
    override fun Widget(isVisible: Boolean, hideDialog: () -> Unit, onAcceptClicked: () -> Unit) {
        Content(isVisible, hideDialog = hideDialog, onAcceptClicked = onAcceptClicked)
    }
}

@Composable
private fun Content(
    isVisible: Boolean,
    hideDialog: () -> Unit,
    onAcceptClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    DialogBase(
        isVisible = isVisible,
        isVisibleActions = true,
        titleBtnCancel = text.shared.btnTitleCancel,
        titleBtnAccept = text.shared.btnTitleCreate,
        onAcceptClicked = onAcceptClicked,
        onCancelClicked = hideDialog
    ) {
        Column(modifier.fillMaxWidth()) {
            Text(
                "ПИН-код не найден",
                style = TextDesign.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth().padding(bottom = dp16)
            )
            Text(
                "Установите ПИН-код чтобы использовать доступ к скрытым заметкам по сканеру.",
                style = TextDesign.bodyMedium,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}