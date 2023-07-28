package ru.maksonic.beresta.feature.hidden_notes_dialog.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 15.07.2023
 */
interface HiddenNotesApi {
    interface Feature {
        val pinCode: Flow<String>
        suspend fun updatePassword(new: String)
    }

    interface Ui {

        interface EnterPasswordDialog {
            val visibility: SharedUiState<Boolean>

            @Composable
            fun Widget(onSuccessPin: () -> Unit)
        }
    }
}