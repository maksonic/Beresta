package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 04.09.2023
 */
@Serializable
data class LangComponentUiError(
    @SerialName("error_ui_fetched_chips_not_found") val fetchedChipsNotFoundError: String,
    @SerialName("error_ui_fail_pin_creation") val failCreationCode: String,
    @SerialName("error_ui_fail_pin_verification") val failVerificationCode: String,
) {
    companion object {
        val Default = LangComponentUiError(
            fetchedChipsNotFoundError = "Failed to load folders",
            failCreationCode = "Error. PIN codes did not match.",
            failVerificationCode = "Error. Invalid PIN."
        )
    }
}