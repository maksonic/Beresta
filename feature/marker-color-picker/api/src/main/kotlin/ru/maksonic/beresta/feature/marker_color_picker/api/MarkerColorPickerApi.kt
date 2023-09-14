package ru.maksonic.beresta.feature.marker_color_picker.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * @Author maksonic on 09.09.2023
 */
interface MarkerColorPickerApi {
    interface Ui {
        val sharedState: State<MarkerPickerState>
        fun showWithColor(colorId: Long)
        fun updateVisibility(value: Boolean)
        fun updateColorId(id: Long)

        @Composable
        fun Widget(onAcceptClicked: (colorId: Long) -> Unit)
    }
}