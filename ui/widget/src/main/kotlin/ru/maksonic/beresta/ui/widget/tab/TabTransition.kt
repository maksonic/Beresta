package ru.maksonic.beresta.ui.widget.tab

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 10.03.2023
 */
// Tab transition specifications
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100

@Composable
internal fun TabTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable () -> Unit
) {
    val transition = updateTransition(selected, label = "")
    val color by transition.animateColor(
        transitionSpec = {
            if (false isTransitioningTo true) {
                tween(
                    durationMillis = TabFadeInAnimationDuration,
                    delayMillis = TabFadeInAnimationDelay,
                    easing = LinearEasing
                )
            } else {
                tween(
                    durationMillis = TabFadeOutAnimationDuration,
                    easing = LinearEasing
                )
            }
        }, label = ""
    ) {
        if (it) activeColor else inactiveColor
    }
    CompositionLocalProvider(
        LocalContentColor provides color.copy(alpha = 1f),
        LocalContentAlpha provides color.alpha,
        content = content
    )
}