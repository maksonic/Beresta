package ru.maksonic.beresta.feature.onboarding.data.lang

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 12.10.2023
 */
@Serializable
data class OnboardingsLangContainer(
    @SerialName("russian") val russian: OnboardingsListModel,
    @SerialName("english") val english: OnboardingsListModel,
    @SerialName("chinese") val chinese: OnboardingsListModel,
    @SerialName("chinese_tr") val chineseTr: OnboardingsListModel,
)