package ru.maksonic.beresta.feature.language_selector.api.feature

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class Onboarding(
    @SerialName("btn_title_next")
    val btnTitleNext: String,
    @SerialName("btn_title_sync")
    val btnTitleSync: String,
    @SerialName("btn_title_skip_sync")
    val btnTitleSkipSync: String,
)