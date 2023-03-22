package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 15.03.2023
 */
val LocalAppTonal = staticCompositionLocalOf<AppTonal> {
    error("No dimens provided")
}

data class AppTonal(
    val Level0: Dp,
    val Level1: Dp,
    val Level2: Dp,
    val Level3: Dp,
    val Level4: Dp,
    val Level5: Dp,
)

val tonalElevations = AppTonal(
    Level0 = 0.dp,
    Level1 = 1.dp,
    Level2 = 2.dp,
    Level3 = 3.dp,
    Level4 = 4.dp,
    Level5 = 5.dp,
)