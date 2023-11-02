package ru.maksonic.beresta.common.ui_kit.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
        ), label = ""
    )

    this.scale(scale)
}