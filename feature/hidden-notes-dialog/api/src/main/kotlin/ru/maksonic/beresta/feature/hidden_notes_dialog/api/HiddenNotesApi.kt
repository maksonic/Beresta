package ru.maksonic.beresta.feature.hidden_notes_dialog.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinSecureUiState

/**
 * @Author maksonic on 15.07.2023
 */
interface HiddenNotesApi {
    interface Feature {
        val pinCode: Flow<String>
        suspend fun updatePassword(new: String)

        interface SecurePrefs {
            val state: Flow<PinSecureUiState>
            suspend fun updatePinVisibility(isVisible: Boolean)
            suspend fun updateKeyTapVisibility(isVisible: Boolean)
        }
    }

    interface Ui {

        interface EnterPasswordDialog {

            @Composable
            fun Widget(
                hideDialog: () -> Unit,
                onSuccessPin: () -> Unit,
                isBlocked: Boolean,
                onBlockedBackPressed: () -> Unit
            )
        }
    }
}