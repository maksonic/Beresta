package ru.maksonic.beresta.common.ui_theme.provide

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
    val level0: Dp,
    val level1: Dp,
    val level2: Dp,
    val level3: Dp,
    val level4: Dp,
    val level5: Dp,
)

val elevations = AppElevation(
    level0 = 0.0.dp,
    level1 = 1.0.dp,
    level2 = 3.0.dp,
    level3 = 6.0.dp,
    level4 = 8.0.dp,
    level5 = 12.0.dp,
)