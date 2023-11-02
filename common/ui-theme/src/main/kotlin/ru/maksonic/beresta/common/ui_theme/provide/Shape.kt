package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppShape = staticCompositionLocalOf<AppShape> {
    error("No shapes provided")
}

data class AppShape(
    val primaryBtn: Shape,
    val cornerNone: Shape,
    val cornerMinimal: Shape,
    val cornerSmall: Shape,
    val cornerNormal: Shape,
    val cornerLarge: Shape,
    val cornerExtra: Shape,
    val cornerRound: Shape,
    val dp1BorderWidth: Dp,
    val dp2BorderWidth: Dp,
    val emojiBig: TextUnit
)

val shapes = AppShape(
    primaryBtn = RoundedCornerShape(50.dp),
    cornerNone = RoundedCornerShape(0.dp),
    cornerMinimal = RoundedCornerShape(2.dp),
    cornerSmall = RoundedCornerShape(4.dp),
    cornerNormal = RoundedCornerShape(8.dp),
    cornerLarge = RoundedCornerShape(16.dp),
    cornerExtra = RoundedCornerShape(28.dp),
    cornerRound = RoundedCornerShape(50.dp),
    dp1BorderWidth = 1.dp,
    dp2BorderWidth = 2.dp,
    emojiBig = 56.sp
)