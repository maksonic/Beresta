package ru.maksonic.beresta.feature.marker_color_picker.ui.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiApi
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiState

/**
 * @Author maksonic on 29.10.2023
 */
class MarkerPickerUiCore : MarkerPickerUiApi {
    @Composable
    override fun Widget(
        state: MarkerPickerUiState,
        onUpdateCurrentColorSelectionClicked: (colorId: Long) -> Unit,
        hideDialog: () -> Unit
    ) {
        Container(
            state = state,
            onUpdateCurrentColorSelectionClicked = onUpdateCurrentColorSelectionClicked,
            hideDialog = hideDialog
        )
    }
}