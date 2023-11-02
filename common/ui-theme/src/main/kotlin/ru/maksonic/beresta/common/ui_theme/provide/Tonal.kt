package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.AppColor
import ru.maksonic.beresta.common.ui_theme.colors.surface
import kotlin.math.ln

/**
 * @Author maksonic on 15.03.2023
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

@Composable
fun Dp.toColor(): Color {
    val absoluteElevation = LocalAbsoluteTonalElevation.current + this
    return surfaceColorAtElevation(color = surface, elevation = absoluteElevation)
}

val LocalAppTonal = staticCompositionLocalOf<AppTonal> {
    error("No dimens provided")
}

data class AppTonal(
    val level0: Dp, val level1: Dp, val level2: Dp, val level3: Dp, val level4: Dp, val level5: Dp
)

val tonalElevations = AppTonal(0.dp, 1.dp, 2.dp, 3.dp, 4.dp, 5.dp)

object TonalElevation {
    val Level0 @Composable get() = tonalElevations.level0.toColor()
    val Level1 @Composable get() = tonalElevations.level1.toColor()
    val Level2 @Composable get() = tonalElevations.level2.toColor()
    val Level3 @Composable get() = tonalElevations.level3.toColor()
    val Level4 @Composable get() = tonalElevations.level4.toColor()
    val Level5 @Composable get() = tonalElevations.level5.toColor()
}