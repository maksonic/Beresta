package ru.maksonic.beresta.feature.onboarding.ui.core.widget

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import ru.maksonic.beresta.common.ui_kit.animation.pulsating
import ru.maksonic.beresta.common.ui_kit.helpers.lerp
import ru.maksonic.beresta.common.ui_theme.Theme

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
internal fun AnimatedOnboardingImage(
    painter: Painter,
    pageOffset: State<Float>,
    pagerProgress: State<Boolean>,
    modifier: Modifier = Modifier
) {

    val transitionY = if (pagerProgress.value) -pageOffset.value * 500 else pageOffset.value * -500
    val rotation = if (pagerProgress.value) pageOffset.value * 20 else pageOffset.value * -20
    val animateTransition = animateFloatAsState(targetValue = transitionY, label = "")
    val animateRotation = animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(Theme.dimen.durationAnimOnboarding, easing = LinearOutSlowInEasing),
        label = ""
    )

    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                lerp(
                    start = 0.4f,
                    stop = 1f,
                    fraction = 1f - pageOffset.value.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                    rotationY = animateRotation.value
                    translationY = animateTransition.value
                }
            }
            .pulsating(pulseFraction = 1.05f, duration = 3000)
    )
}