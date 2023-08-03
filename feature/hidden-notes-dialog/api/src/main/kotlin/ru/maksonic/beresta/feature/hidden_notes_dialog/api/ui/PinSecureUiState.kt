package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

/**
 * @Author maksonic on 03.08.2023
 */
data class PinSecureUiState(
    val isVisible: Boolean,
    val isVisibleKeyboardTap: Boolean
) {
    companion object {
        val INITIAL = PinSecureUiState(isVisible = false, isVisibleKeyboardTap = true)
    }
}