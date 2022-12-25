package ru.maksonic.beresta.feature.onboarding.ui.widget

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.lerp
import kotlin.math.absoluteValue

/**
 * @Author maksonic on 25.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun AnimatedOnboardingImage(
    pagerScope: PagerScope,
    page: Int,
    image: Int,
    modifier: Modifier = Modifier
) {
    val pageOffset = pagerScope.calculateCurrentOffsetForPage(page).absoluteValue
    val pagerProgress = pagerScope.currentPageOffset > 0
    val transitionY = if (pagerProgress) -pageOffset * 500 else pageOffset * -500
    val rotation = if (pagerProgress) pageOffset * 20 else pageOffset * -20

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
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                    rotationY = animateRotation.value
                    translationY = animateTransition.value
                }
            }
    )
}