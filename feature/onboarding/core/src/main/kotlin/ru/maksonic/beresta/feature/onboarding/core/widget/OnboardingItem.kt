package ru.maksonic.beresta.feature.onboarding.core.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.onboarding.core.OnboardingUi
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 15.12.2022
 */
@Composable
internal fun OnboardingItem(
    item: OnboardingUi,
    image: Int,
    pageOffset: State<Float>,
    pagerProgress: State<Boolean>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {

        AnimatedOnboardingImage(
            pageOffset = pageOffset,
            pagerProgress = pagerProgress,
            imageId = image,
            modifier
                .padding(top = Theme.widgetSize.topBarNormalHeight)
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
                style = TextDesign.header,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.weight(0.05f))
            Text(
                text = item.description,
                style = TextDesign.bodyPrimary,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.weight(0.2f))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OnboardingItemPreview() {
    BerestaTheme {
        OnboardingItem(
            item = OnboardingUi.Preview,
            pagerProgress =  remember { mutableStateOf(false) } ,
            pageOffset = remember { mutableStateOf(0F) },
            image = ru.maksonic.beresta.ui.theme.R.drawable.splash_logo,
        )
    }
}

