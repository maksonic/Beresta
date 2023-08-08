package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

/**
 * @Author maksonic on 03.08.2023
 */
data class PinVisibilityUiState(
    val isVisible: Boolean,
    val isVisibleKeyboardTap: Boolean
) {
    companion object {
        val INITIAL = PinVisibilityUiState(isVisible = false, isVisibleKeyboardTap = true)
    }
}