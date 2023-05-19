package ru.maksonic.beresta.ui.theme.color

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 15.03.2023
 */
sealed class StateLayerOpacity {
    object Hover : StateLayerOpacity()
    object Focus : StateLayerOpacity()
    object Pressed: StateLayerOpacity()
    object Dragged: StateLayerOpacity()
}

fun Color.stateLayerOpacity(state: StateLayerOpacity): Color {
    val opacity = when (state) {
        is StateLayerOpacity.Hover -> 0.08f
        StateLayerOpacity.Dragged -> 0.12f
        StateLayerOpacity.Focus -> 0.12f
        StateLayerOpacity.Pressed -> 0.16f
    }
    return this.copy(alpha = opacity)
}