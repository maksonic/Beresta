package ru.maksonic.beresta.feature.image_viewer.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.colors.primary

/**
 * @Author maksonic on 02.12.2023
 */
@Composable
fun Container(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize().background(primary))
}