package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.debugInspectorInfo

/**
 * @Author maksonic on 15.02.2023
 */
fun Modifier.pulsating(
    pulseFraction: Float = 1.2f,
    duration: Int = 1000,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "pulsating"
        properties["pulseFraction"] = pulseFraction
        properties["duration"] = duration
    }
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = RepeatMode.Reverse
        )
    )

    this.scale(scale)
}