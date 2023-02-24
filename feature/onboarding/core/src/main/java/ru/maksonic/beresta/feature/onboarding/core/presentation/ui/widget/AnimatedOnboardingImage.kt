package ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.animation.pulsating
import ru.maksonic.beresta.ui.widget.functional.lerp

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
internal fun AnimatedOnboardingImage(
    pageOffset: () -> Float,
    pagerProgress: () -> Boolean,
    image: Int,
    modifier: Modifier = Modifier
) {

    val transitionY = if (pagerProgress()) -pageOffset() * 500 else pageOffset() * -500
    val rotation = if (pagerProgress()) pageOffset() * 20 else pageOffset() * -20
    val animateTransition = animateFloatAsState(targetValue = transitionY)
    val animateRotation = animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(Theme.dimen.durationAnimOnboarding, easing = LinearOutSlowInEasing)
    )

    Image(
        painter = painterResource(image),
        contentDescription = "",
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                lerp(
                    start = 0.4f,
                    stop = 1f,
                    fraction = 1f - pageOffset().coerceIn(0f, 1f)
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