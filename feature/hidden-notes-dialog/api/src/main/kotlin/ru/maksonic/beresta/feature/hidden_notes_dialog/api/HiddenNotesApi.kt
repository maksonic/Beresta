package ru.maksonic.beresta.feature.hidden_notes_dialog.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinInputVisibility

/**
 * @Author maksonic on 15.07.2023
 */
interface HiddenNotesApi {
    interface Feature {
        val pinCode: Flow<String>
        suspend fun updatePassword(new: String)

        interface PinPrivacyState {
            val state: Flow<PinInputVisibility>
            suspend fun updatePinVisibility(isVisible: Boolean)
            suspend fun updateKeyTapVisibility(isVisible: Boolean)
        }

        interface PinFailCounter {
            val state: Flow<PinInfo>
            suspend fun updateFailCounter(value: Int)
            suspend fun updateCoolDown(isCoolDown: Boolean, endTime: Long)
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