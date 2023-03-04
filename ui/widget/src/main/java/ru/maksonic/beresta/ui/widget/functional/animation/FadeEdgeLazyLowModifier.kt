package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 04.03.2023
 */
fun Modifier.rowFadingEdge(
    isVisibleStartEdge: Boolean = true,
    isVisibleEndEdge: Boolean = true,
    startEdgeInitialColor: Color = Color.White,
    startEdgeTargetColor: Color = Color.Transparent,
    endEdgeInitialColor: Color = startEdgeTargetColor,
    endEdgeTargetColor: Color = startEdgeInitialColor,
    fadeStartEdgeLength: Float = 100f,
    fadeEndEdgeLength: Float = 100f,
) = this.then(
    composed {
        val startEdgeLengthAnim = animateFloatAsState(
            targetValue = if (isVisibleStartEdge) fadeStartEdgeLength else .1f,
            animationSpec = tween()
        )
        val endEdgeLengthAnim = animateFloatAsState(
            targetValue = if (isVisibleEndEdge) fadeEndEdgeLength else .1f, animationSpec = tween()
        )

        drawWithContent {
            val startEdgeColors = listOf(startEdgeInitialColor, startEdgeTargetColor)
            val endEdgeColors = listOf(endEdgeInitialColor, endEdgeTargetColor)
            drawContent()
            if (isVisibleStartEdge) {
                drawRect(
                    brush = Brush.horizontalGradient(
                        startEdgeColors,
                        endX = startEdgeLengthAnim.value
                    )
                )
            }
            if (isVisibleEndEdge) {
                drawRect(
                    brush = Brush.horizontalGradient(
                        endEdgeColors,
                        startX = size.width - endEdgeLengthAnim.value,
                        endX = size.width
                    ),
                )
            }
        }
    }
)