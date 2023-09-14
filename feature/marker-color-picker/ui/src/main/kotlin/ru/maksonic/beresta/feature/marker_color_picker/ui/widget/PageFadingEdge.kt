package ru.maksonic.beresta.feature.marker_color_picker.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 11.09.2023
 */
@Composable
internal fun PageFadingEdge(modifier: Modifier, height: Dp) {
    Row(modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
        val edgeColors = listOf(secondaryContainer, transparent)

        Canvas(
            modifier
                .height(height)
                .width(dp16),
            onDraw = { drawRect(Brush.horizontalGradient(edgeColors)) }
        )

        Canvas(
            modifier
                .height(height)
                .width(dp16),
            onDraw = { drawRect(Brush.horizontalGradient(edgeColors.reversed())) }
        )
    }
}