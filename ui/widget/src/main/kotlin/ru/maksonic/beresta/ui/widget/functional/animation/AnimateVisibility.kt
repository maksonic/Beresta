package ru.maksonic.beresta.ui.widget.functional.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 21.01.2023
 */
@Composable
fun AnimateFadeInOut(
    visible: Boolean,
    fadeInDuration: Int = Theme.animSpeed.fadeIn,
    fadeOutDuration: Int = Theme.animSpeed.fadeOut,
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
        enter = fadeIn(tween(Theme.animSpeed.fadeIn)) + expandIn(tween(Theme.animSpeed.fadeIn)),
        exit = shrinkOut(tween(Theme.animSpeed.fadeOut)) + fadeOut(tween(Theme.animSpeed.fadeOut))
    ) {
        content()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <S> AnimateContent(
    state: S,
    animSpeed: Int = Theme.animSpeed.common,
    content: @Composable (state: S) -> Unit
) {
    AnimatedContent(targetState = state, transitionSpec = {
        fadeIn(animationSpec = tween(animSpeed, delayMillis = 90)) +
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(animSpeed, delayMillis = 90)
                ) with
                fadeOut(animationSpec = tween(90))
    }, label = "") { state ->
        content(state)
    }
}