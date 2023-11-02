package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 15.07.2023
 */
interface HiddenNotesDialogUiApi {
    interface NotCreatedPinDialog {
        @Composable
        fun Widget(
            isVisible: Boolean,
            hideDialog: () -> Unit,
            onAcceptClicked: () -> Unit
        )
    }

    interface PinInputDialog {
        @Composable
        fun Widget(
            isVisible: Boolean,
            hideDialog: () -> Unit,
            onSuccessPin: () -> Unit,
            isBlocked: Boolean,
            onBlockedBackPressed: () -> Unit
        )
    }
}