package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.color.toColor

/**
 * @Author maksonic on 15.03.2023
 */
val LocalAppTonal = staticCompositionLocalOf<AppTonal> {
    error("No dimens provided")
}

data class AppTonal(
    val Level0: Dp, val Level1: Dp, val Level2: Dp, val Level3: Dp, val Level4: Dp, val Level5: Dp
)

val tonalElevations = AppTonal(0.dp, 1.dp, 2.dp, 3.dp, 4.dp, 5.dp)

object TonalElevationToken {
    val Level0 @Composable get() = tonalElevations.Level0.toColor()
    val Level1 @Composable get() = tonalElevations.Level1.toColor()
    val Level2 @Composable get() = tonalElevations.Level2.toColor()
    val Level3 @Composable get() = tonalElevations.Level3.toColor()
    val Level4 @Composable get() = tonalElevations.Level4.toColor()
    val Level5 @Composable get() = tonalElevations.Level5.toColor()
}