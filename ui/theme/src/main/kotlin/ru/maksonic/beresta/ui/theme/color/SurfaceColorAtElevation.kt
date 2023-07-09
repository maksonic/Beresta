package ru.maksonic.beresta.ui.theme.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import kotlin.math.ln

/**
 * @Author maksonic on 08.07.2023
 */
@Composable
fun surfaceColorAtElevation(color: Color, elevation: Dp): Color =
    if (color == Theme.color.surface) Theme.color.surfaceColorAtElevation(elevation) else color

fun AppColor.surfaceColorAtElevation(
    elevation: Dp,
): Color {
    if (elevation == 0.dp) return surface
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return surfaceTint.copy(alpha = alpha).compositeOver(surface)
}
