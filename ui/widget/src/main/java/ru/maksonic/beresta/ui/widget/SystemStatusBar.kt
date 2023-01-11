package ru.maksonic.beresta.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 11.01.2023
 */
@Composable
fun SystemStatusBar(modifier: Modifier) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Box(modifier = modifier
        .fillMaxWidth()
        .height(statusBarHeight)
        .background(Color.Red))
}