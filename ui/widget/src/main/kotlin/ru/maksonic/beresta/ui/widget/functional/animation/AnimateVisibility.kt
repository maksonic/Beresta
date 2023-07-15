package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 21.01.2023
 */
@Composable
fun AnimateFadeInOut(
    visible: Boolean,
    fadeInDuration: Int = Theme.animVelocity.fadeIn,
    fadeOutDuration: Int = Theme.animVelocity.fadeOut,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(fadeInDuration)),
        exit = fadeOut(tween(fadeOutDuration))
    ) {
        content()
    }
}

@Composable
fun AnimateVisibility(isVisible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(tween(Theme.animVelocity.fadeIn)) + expandIn(tween(Theme.animVelocity.fadeIn)),
        exit = shrinkOut(tween(Theme.animVelocity.fadeOut)) + fadeOut(tween(Theme.animVelocity.fadeOut))
    ) {
        content()
    }
}

@Composable
fun <S> AnimateContent(
    state: S,
    animSpeed: Int = Theme.animVelocity.common,
    content: @Composable (state: S) -> Unit
) {
    AnimatedContent(targetState = state, transitionSpec = {
        (fadeIn(animationSpec = tween(animSpeed, delayMillis = 90)) +
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(animSpeed, delayMillis = 90)
                )).togetherWith(fadeOut(animationSpec = tween(90)))
    }, label = "") { value ->
        content(value)
    }
}