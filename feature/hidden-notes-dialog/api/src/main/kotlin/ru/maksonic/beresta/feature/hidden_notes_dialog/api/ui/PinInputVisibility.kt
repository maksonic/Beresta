package ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui

/**
 * @Author maksonic on 03.08.2023
 */
data class PinInputVisibility(
    val isVisiblePin: Boolean,
    val isVisibleOnKeyboardTap: Boolean
) {
    companion object {
        val INITIAL = PinInputVisibility(isVisiblePin = false, isVisibleOnKeyboardTap = true)
    }
}