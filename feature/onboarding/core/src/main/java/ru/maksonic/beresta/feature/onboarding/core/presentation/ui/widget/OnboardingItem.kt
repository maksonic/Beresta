package ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import ru.maksonic.beresta.feature.onboarding.core.data.OnboardingEntity
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 15.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun OnboardingItem(
    item: OnboardingEntity,
    page: Int,
    pagerScope: PagerScope,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Spacer(modifier = modifier.weight(0.2f))

        AnimatedOnboardingImage(pagerScope, page, item.image, modifier.weight(0.5f))

        Spacer(modifier = modifier.weight(0.1f))

        Text(
            text = item.title,
            style = TextDesign.header,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(start = dp16, end = dp16)
        )

        Spacer(modifier = modifier.weight(0.02f))

        Text(
            text = item.description,
            style = TextDesign.body,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(start = dp16, end = dp16)
        )

        Spacer(modifier = modifier.weight(0.2f))
    }
}

