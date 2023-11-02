package ru.maksonic.beresta.feature.onboarding.ui.core.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.onboarding.domain.Onboarding

/**
 * @Author maksonic on 15.12.2022
 */
@Composable
internal fun OnboardingItem(
    item: Onboarding,
    pageOffset: State<Float>,
    pagerProgress: State<Boolean>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {

        AnimatedOnboardingImage(
            painter = painterResource(item.image),
            pageOffset = pageOffset,
            pagerProgress = pagerProgress,
            modifier
                .padding(top = Theme.size.topBarNormalHeight)
                .weight(0.5f)
        )

        Column(
            modifier
                .weight(0.5f)
                .padding(start = dp16, end = dp16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.weight(0.05f))
            Text(
                text = item.title,
                style = TextDesign.titleLarge,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.weight(0.05f))
            Text(
                text = item.description,
                style = TextDesign.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.weight(0.2f))
        }
    }
}


@Preview
@Composable
private fun OnboardingItemPreview() {
    PreviewContainer {
        OnboardingItem(
            item = Onboarding(
                id = 111,
                title = "Title",
                description = "Description",
                ru.maksonic.beresta.common.ui_theme.R.drawable.maksonic_logo_low
            ),
            pagerProgress = remember { mutableStateOf(false) },
            pageOffset = remember { mutableFloatStateOf(0F) },
        )
    }
}

