package ru.maksonic.beresta.feature.marker_color_picker.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * @Author maksonic on 09.09.2023
 */
interface MarkerColorPickerApi {
    interface Ui {
        val visibilityState: State<Boolean>
        fun updateVisibility(value: Boolean)

        @Composable
        fun Widget()
    }
}