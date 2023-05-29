package ru.maksonic.beresta.ui.theme.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppShape = staticCompositionLocalOf<AppShape> {
    error("No shapes provided")
}

data class AppShape(
    val primaryBtn: Shape,
    val cornerNone: Shape,
    val cornerSmall: Shape,
    val cornerNormal: Shape,
    val cornerBig: Shape,
    val cornerExtra: Shape,
    val cornerRound: Shape,
    val dp1BorderWidth: Dp,
    val dp2BorderWidth: Dp,
    val emojiBig: TextUnit
)

val shapes = AppShape(
    primaryBtn = RoundedCornerShape(50.dp),
    cornerNone = RoundedCornerShape(0.dp),
    cornerSmall = RoundedCornerShape(4.dp),
    cornerNormal = RoundedCornerShape(8.dp),
    cornerBig = RoundedCornerShape(16.dp),
    cornerExtra = RoundedCornerShape(28.dp),
    cornerRound = RoundedCornerShape(50.dp),
    dp1BorderWidth = 1.dp,
    dp2BorderWidth = 2.dp,
    emojiBig = 56.sp
)

object Shape {
    val primaryBtn @Composable get() = Theme.shape.primaryBtn
    val cornerNone @Composable get() = Theme.shape.cornerNone
    val cornerSmall @Composable get() = Theme.shape.cornerSmall
    val cornerNormal @Composable get() = Theme.shape.cornerNormal
    val cornerBig @Composable get() = Theme.shape.cornerBig
    val cornerExtra @Composable get() = Theme.shape.cornerExtra
    val cornerRound @Composable get() = Theme.shape.cornerRound
}