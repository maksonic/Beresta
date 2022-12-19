package ru.maksonic.beresta.feature.onboarding.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.lerp
import kotlin.math.absoluteValue

/**
 * @Author maksonic on 15.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun OnboardingItem(
    currentItem: OnboardingEntity,
    page: Int,
    pagerScope: PagerScope,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Spacer(modifier = modifier.weight(0.2f))

        AnimatedOnboardingImage(pagerScope, page, currentItem.image, modifier.weight(0.5f))

        Spacer(modifier = modifier.weight(0.1f))

        Text(
            text = currentItem.title,
            style = TextDesign.header,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(start = dp16, end = dp16)
        )

        Spacer(modifier = modifier.weight(0.02f))

        Text(
            text = currentItem.description,
            style = TextDesign.body,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(start = dp16, end = dp16)
        )

        Spacer(modifier = modifier.weight(0.2f))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AnimatedOnboardingImage(
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