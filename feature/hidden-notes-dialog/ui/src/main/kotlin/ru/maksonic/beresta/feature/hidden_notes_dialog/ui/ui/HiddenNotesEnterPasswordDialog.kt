package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi

/**
 * @Author maksonic on 15.07.2023
 */
class HiddenNotesEnterPasswordDialog : HiddenNotesApi.Ui.EnterPasswordDialog {
    override val visibility: SharedUiState<Boolean> = object : SharedUiState<Boolean>(false) {}

    @Composable
    override fun Widget(
        onSuccessPin: () -> Unit,
        isBlocked: Boolean,
        onBlockedBackPressed: () -> Unit,
    ) {
        val visibilityState = visibility.state.collectAsStateWithLifecycle()

        Container(
            visibilityState = visibilityState,
            updateVisibility = { visibility.update(it) },
            onSuccessPin = onSuccessPin,
            isBlocked = isBlocked,
            onBlockedBackPressed = onBlockedBackPressed
        )
    }
}