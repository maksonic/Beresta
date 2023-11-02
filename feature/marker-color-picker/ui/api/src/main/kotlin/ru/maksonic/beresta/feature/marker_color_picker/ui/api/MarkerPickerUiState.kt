package ru.maksonic.beresta.feature.marker_color_picker.ui.api

import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer

/**
 * @Author maksonic on 29.10.2023
 */
data class MarkerPickerUiState(
    val isVisibleDialog: Boolean,
    val colors: List<ColorContainer>,
    val currentSelectedColorId: Long
) {
    companion object {
        val Initial = MarkerPickerUiState(
            isVisibleDialog = false,
            colors = emptyList(),
            currentSelectedColorId = 0L
        )
    }
}

fun MarkerPickerUiState.findColor(id: Long) = this.colors.find { it.colorId == id }
