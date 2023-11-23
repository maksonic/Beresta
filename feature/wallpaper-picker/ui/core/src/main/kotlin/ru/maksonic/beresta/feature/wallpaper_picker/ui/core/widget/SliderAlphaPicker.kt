package ru.maksonic.beresta.feature.wallpaper_picker.ui.core.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.provide.dp16

/**
 * @Author maksonic on 21.11.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SliderAlphaPicker(
    color: Color,
    initialAlpha: Float,
    currentAlpha: Float,
    updateAlpha: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier
) {
    var alpha by remember { mutableStateOf(currentAlpha) }

    Slider(
        value = alpha,
        valueRange = valueRange,
        onValueChange = {
            alpha = it
            updateAlpha(alpha)
        },
        thumb = { Thumb() },
        track = { Track(color, initialAlpha) },
        modifier = modifier
            .padding(dp16)
            .fillMaxWidth(0.7f)
    )
}

@Composable
private fun Thumb(modifier: Modifier = Modifier) {
    Canvas(
        modifier
            .size(36.dp)
            .border(2.dp, Color.White, CircleShape)) {}
}

@Composable
private fun Track(color: Color, initialAlpha: Float, modifier: Modifier = Modifier) {
    val brush = Brush.linearGradient(listOf(color.copy(initialAlpha), color))

    Canvas(
        modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(CircleShape), onDraw = { drawRect(brush) })
}