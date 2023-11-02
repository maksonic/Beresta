package ru.maksonic.beresta.common.ui_kit.bar.system

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color


/**
 * @Author maksonic on 12.01.2023
 */
val SystemNavigationBarHeight
    @Composable get() = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

@Composable
fun SystemNavigationBar(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    backgroundColor: State<Color> = remember { mutableStateOf(color) },
) {
    val height = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    Box(modifier = modifier
        .fillMaxWidth()
        .height(height)
        .drawBehind { drawRect(backgroundColor.value) })
}

fun systemNavBarHeightDp(context: Context): Int {
    val resources = context.resources
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}