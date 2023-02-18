package ru.maksonic.beresta.feature.language_selector.api.languages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.feature.language_selector.api.feature.Onboarding

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class Russian(
    @SerialName("onboarding")
    val onboarding: Onboarding
)