package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 04.09.2023
 */
@Serializable
data class LangUiErrorData(
    @SerializedName("error_ui_fetched_chips_not_found")
    val fetchedChipsNotFoundError: String = "",
    @SerializedName("error_ui_fail_pin_creation") val failCreationCode: String = "",
    @SerializedName("error_ui_fail_pin_verification") val failVerificationCode: String = "",
)