package ru.maksonic.beresta.feature.marker_color_picker.ui.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 29.10.2023
 */
interface MarkerPickerUiApi {
    @Composable
    fun Widget(
        state: MarkerPickerUiState,
        onUpdateCurrentColorSelectionClicked: (colorId: Long) -> Unit,
        hideDialog: () -> Unit,
    )
}