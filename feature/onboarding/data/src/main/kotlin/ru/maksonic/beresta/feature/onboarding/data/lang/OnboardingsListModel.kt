package ru.maksonic.beresta.feature.onboarding.data.lang

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 12.10.2023
 */
@Serializable
data class OnboardingsListModel(
    @SerialName("onboardings")
    val list: List<OnboardingItemModel>
)