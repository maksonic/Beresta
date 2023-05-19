package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppElevation = staticCompositionLocalOf<AppElevation> {
    error("No elevation provided")
}

data class AppElevation(
    val Level0: Dp,
    val Level1: Dp,
    val Level2: Dp,
    val Level3: Dp,
    val Level4: Dp,
    val Level5: Dp,
)

val elevations = AppElevation(
    Level0 = 0.0.dp,
    Level1 = 1.0.dp,
    Level2 = 3.0.dp,
    Level3 = 6.0.dp,
    Level4 = 8.0.dp,
    Level5 = 12.0.dp,
)