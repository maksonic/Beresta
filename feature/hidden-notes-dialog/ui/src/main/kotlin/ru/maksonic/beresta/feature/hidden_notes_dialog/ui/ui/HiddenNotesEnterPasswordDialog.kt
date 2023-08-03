package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi

/**
 * @Author maksonic on 15.07.2023
 */
class HiddenNotesEnterPasswordDialog : HiddenNotesApi.Ui.EnterPasswordDialog {
    @Composable
    override fun Widget(
        hideDialog: () -> Unit,
        onSuccessPin: () -> Unit,
        isBlocked: Boolean,
        onBlockedBackPressed: () -> Unit,
    ) {
        Container(
            hideDialog = hideDialog,
            onSuccessPin = onSuccessPin,
            isBlocked = isBlocked,
            onBlockedBackPressed = onBlockedBackPressed
        )
    }
}