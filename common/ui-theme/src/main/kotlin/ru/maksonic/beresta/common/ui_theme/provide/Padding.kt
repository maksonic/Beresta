package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppPadding = staticCompositionLocalOf<AppPadding> {
    error("No paddings provided")
}

data class AppPadding(
    val dp0: Dp,
    val dp1: Dp,
    val dp2: Dp,
    val dp4: Dp,
    val dp6: Dp,
    val dp8: Dp,
    val dp10: Dp,
    val dp12: Dp,
    val dp16: Dp,
    val dp24: Dp,
    val dp32: Dp,
    val dp36: Dp,
    val dp48: Dp,
    val dp56: Dp,
    val dp64: Dp,
    val dp72: Dp,
)

val paddings = AppPadding(
    dp0 = 0.dp,
    dp1 = 1.dp,
    dp2 = 2.dp,
    dp4 = 4.dp,
    dp6 = 6.dp,
    dp8 = 8.dp,
    dp10 = 10.dp,
    dp12 = 12.dp,
    dp16 = 16.dp,
    dp24 = 24.dp,
    dp32 = 32.dp,
    dp36 = 36.dp,
    dp48 = 48.dp,
    dp56 = 56.dp,
    dp64 = 64.dp,
    dp72 = 72.dp
)

val dp0 @Composable get() = Theme.padding.dp0
val dp1 @Composable get() = Theme.padding.dp1
val dp2 @Composable get() = Theme.padding.dp2
val dp4 @Composable get() = Theme.padding.dp4
val dp6 @Composable get() = Theme.padding.dp6
val dp8 @Composable get() = Theme.padding.dp8
val dp10 @Composable get() = Theme.padding.dp10
val dp12 @Composable get() = Theme.padding.dp12
val dp16 @Composable get() = Theme.padding.dp16
val dp24 @Composable get() = Theme.padding.dp24
val dp32 @Composable get() = Theme.padding.dp32
val dp36 @Composable get() = Theme.padding.dp36
val dp48 @Composable get() = Theme.padding.dp48
val dp56 @Composable get() = Theme.padding.dp56
val dp64 @Composable get() = Theme.padding.dp64
val dp72 @Composable get() = Theme.padding.dp72