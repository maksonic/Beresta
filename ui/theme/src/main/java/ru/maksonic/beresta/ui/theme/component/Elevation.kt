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
    val disable: Dp,
    val dp1: Dp,
    val dp2: Dp,
    val dp4: Dp,
    val dp8: Dp,
    val dp16: Dp,
    val topBarScrollable: Dp
)

val elevations = AppElevation(
    disable = 0.dp,
    dp1 = 1.dp,
    dp2 = 2.dp,
    dp4 = 4.dp,
    dp8 = 8.dp,
    dp16 = 16.dp,
    topBarScrollable = 4.dp
)