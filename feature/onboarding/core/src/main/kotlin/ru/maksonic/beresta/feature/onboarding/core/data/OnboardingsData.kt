package ru.maksonic.beresta.feature.onboarding.core.data

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.onboarding.core.OnboardingUi
import ru.maksonic.beresta.feature.onboarding.core.R
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 25.04.2023
 */
private const val PAGES_COUNT = 4

internal val onboardings: Array<OnboardingUi>
    @Composable get() = with(text.onboarding.data) {
        buildList {
            repeat(PAGES_COUNT) { index ->
                add(
                    OnboardingUi(
                        title = this@with[index].title, description = this@with[index].description
                    )
                )
            }
        }.toTypedArray()
    }

internal val images = listOf(
    R.drawable.onboarding_image_first,
    R.drawable.onboarding_image_second,
    R.drawable.onboarding_image_third,
    R.drawable.onboarding_image_fourth
)