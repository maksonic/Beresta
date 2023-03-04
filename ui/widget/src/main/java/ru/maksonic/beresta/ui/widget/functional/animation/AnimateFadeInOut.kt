package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 21.01.2023
 */
@Composable
fun AnimateFadeInOut(visible: Boolean, tweenValue: Int = 300, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(tweenValue)),
        exit = fadeOut(tween(tweenValue))
    ) {
        content()
    }
}