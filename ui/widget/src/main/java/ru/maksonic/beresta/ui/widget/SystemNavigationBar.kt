package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 12.01.2023
 */
@Composable
fun SystemNavigationBar(changeableBackgroundColor: () -> Color, modifier: Modifier = Modifier) {
    val height = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Box(modifier = modifier
        .fillMaxWidth()
        .height(height)
        .drawBehind { drawRect(changeableBackgroundColor()) })
}