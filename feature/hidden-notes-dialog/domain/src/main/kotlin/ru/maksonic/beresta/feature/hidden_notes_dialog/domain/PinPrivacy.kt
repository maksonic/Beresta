package ru.maksonic.beresta.feature.hidden_notes_dialog.domain

/**
 * @Author maksonic on 03.08.2023
 */
data class PinPrivacy(
    val isVisibleWhenInputProcess: Boolean,
    val isVisibleOnKeyboardTap: Boolean,
    val isCanUseBiometric: Boolean,
    val isEnabledBiometric: Boolean
) {
    companion object {
        val INITIAL = PinPrivacy(
            isVisibleWhenInputProcess = false,
            isVisibleOnKeyboardTap = true,
            isCanUseBiometric = false,
            isEnabledBiometric = false
        )
    }
}