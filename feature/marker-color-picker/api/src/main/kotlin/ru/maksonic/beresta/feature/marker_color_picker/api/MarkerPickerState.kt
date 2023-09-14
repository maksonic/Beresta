package ru.maksonic.beresta.feature.marker_color_picker.api

/**
 * @Author maksonic on 14.09.2023
 */
data class MarkerPickerState(
    val isVisible: Boolean,
    val colorId: Long
) {
    companion object {
        val Initial = MarkerPickerState(
            isVisible = false,
            colorId = 0L
        )
    }
}