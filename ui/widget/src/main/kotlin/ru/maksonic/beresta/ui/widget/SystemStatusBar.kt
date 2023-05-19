package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 11.01.2023
 */
@Composable
fun SystemStatusBar(backgroundColor: Color = Color.Transparent, modifier: Modifier = Modifier) {
    val height = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Box(modifier = modifier
        .fillMaxWidth()
        .height(height)
        .drawBehind { drawRect(backgroundColor) })
}

val SystemStatusBarHeight
    @Composable get() = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()