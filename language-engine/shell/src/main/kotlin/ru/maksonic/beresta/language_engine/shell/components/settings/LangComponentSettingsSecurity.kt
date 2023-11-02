package ru.maksonic.beresta.language_engine.shell.components.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 03.08.2023
 */
@Serializable
data class LangComponentSettingsSecurity(
    @SerialName("top_bar_title") val topBarTitle: String,
    @SerialName("title_hidden_notes_biometric_visibility") val titleHiddenNotesBiometric: String,
    @SerialName("item_hidden_notes_biometric_visibility") val itemHiddenNotesBiometric: String,
    @SerialName("item_pin_code_visibility") val itemPinVisibility: String,
    @SerialName("item_key_tap_visibility") val itemKeyTapVisibility: String,
    @SerialName("hint_pin_code") val hintPinCode: String,
    @SerialName("description_key_tap_visibility") val descriptionKeyTapVisibility: String,
    @SerialName("description_hidden_notes_biometric") val descriptionHiddenNotesVisibility: String,
) {
    companion object {
        val Default = LangComponentSettingsSecurity(
            topBarTitle = "Safety",
            titleHiddenNotesBiometric = "Hidden notes",
            itemHiddenNotesBiometric = "Unlocking by scanner",
            itemPinVisibility = "PIN code",
            itemKeyTapVisibility = "Show as you type",
            hintPinCode = "Button click effect",
            descriptionKeyTapVisibility = "Used for the numeric keypad when entering a PIN.",
            descriptionHiddenNotesVisibility = "Use the scanner to access hidden notes"
        )
    }
}