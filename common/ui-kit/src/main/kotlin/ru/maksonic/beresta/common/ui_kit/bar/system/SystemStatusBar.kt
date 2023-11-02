package ru.maksonic.beresta.common.ui_kit.bar.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 11.01.2023
 */
val SystemStatusBarHeight
    @Composable get() = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

@Composable
fun SystemStatusBar(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    backgroundColor: State<Color> = remember { mutableStateOf(color) }
) {
    val height = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Box(modifier = modifier
        .fillMaxWidth()
        .height(height)
        .drawBehind { drawRect(backgroundColor.value) })
}
