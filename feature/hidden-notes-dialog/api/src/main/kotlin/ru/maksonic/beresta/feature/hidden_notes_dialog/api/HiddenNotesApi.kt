package ru.maksonic.beresta.feature.hidden_notes_dialog.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinVisibilityUiState
import java.time.LocalDateTime

/**
 * @Author maksonic on 15.07.2023
 */
interface HiddenNotesApi {
    interface Feature {
        val pinCode: Flow<String>
        suspend fun updatePassword(new: String)

        interface SecurePrefs {
            val state: Flow<PinVisibilityUiState>
            suspend fun updatePinVisibility(isVisible: Boolean)
            suspend fun updateKeyTapVisibility(isVisible: Boolean)
        }

        interface PinFailCounter {
            val state: Flow<PinFailInfo>
            suspend fun updateCounter(value: Int)
            suspend fun updateCoolDown(value: Boolean)
            suspend fun updateTick(value: Int)
            suspend fun updateTimestamp(value: LocalDateTime?)
            suspend fun reset()
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