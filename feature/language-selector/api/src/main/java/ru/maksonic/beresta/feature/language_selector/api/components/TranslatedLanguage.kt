package ru.maksonic.beresta.feature.language_selector.api.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 20.02.2023
 */
@Serializable
data class TranslatedLanguage(
    @SerialName("russian") val russian: String = "",
    @SerialName("english") val english: String = "",
    @SerialName("chinese") val chinese: String = "",
)