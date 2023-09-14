package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

/**
 * @Author maksonic on 12.01.2023
 */
fun Modifier.tintSimply(
    color: Color,
): Modifier = graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        this.onDrawWithContent {
            drawContent()
            drawRect(
                color = color,
                blendMode = BlendMode.SrcAtop,
            )
        }
    }