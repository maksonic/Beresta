package ru.maksonic.beresta.feature.onboarding.core.data

import ru.maksonic.beresta.feature.language_selector.api.components.OnboardingDataItem
import ru.maksonic.beresta.feature.onboarding.core.R
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.OnboardingUi

/**
 * @Author maksonic on 15.02.2023
 */
object OnboardingRepository {
    private const val ONBOARDINGS_COUNT = 4
    private val images = listOf(
        R.drawable.first,
        R.drawable.second,
        R.drawable.third,
        R.drawable.fourth,
    )

    fun onboardings(data: Array<OnboardingDataItem>) = Array(ONBOARDINGS_COUNT) {
        OnboardingUi(
            title = data[it].title,
            description = data[it].description,
            imageId = images[it]
        )
    }
}