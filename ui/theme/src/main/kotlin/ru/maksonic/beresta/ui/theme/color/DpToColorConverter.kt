package ru.maksonic.beresta.ui.theme.color

import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * @Author maksonic on 08.07.2023
 */
@Composable
fun Dp.toColor(): Color {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + this
    return surfaceColorAtElevation(color = surface, elevation = absoluteElevation)
}
