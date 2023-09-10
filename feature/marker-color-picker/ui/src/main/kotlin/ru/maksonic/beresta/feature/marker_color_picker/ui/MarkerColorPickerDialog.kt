package ru.maksonic.beresta.feature.marker_color_picker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerColorPickerApi

/**
 * @Author maksonic on 09.09.2023
 */
class MarkerColorPickerDialog : MarkerColorPickerApi.Ui {
    override val visibilityState = mutableStateOf(false)

    override fun updateVisibility(value: Boolean) {
        visibilityState.value = value
    }

    @Composable
    override fun Widget() {
        val visibilityState = rememberSaveable { visibilityState }

        Container(visibilityState, hideDialog = { updateVisibility(false) })
    }
}