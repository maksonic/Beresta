package ru.maksonic.beresta.language_engine.shell.components.settings

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 03.08.2023
 */
@Serializable
data class LangSettingsSecurityData(
    @SerializedName("top_bar_title") val topBarTitle: String = "",
    @SerializedName("item_pin_code_visibility") val itemPinVisibility: String = "",
    @SerializedName("item_key_tap_visibility") val itemKeyTapVisibility: String = "",
    @SerializedName("hint_pin_code") val hintPinCode: String = "",
    @SerializedName("description_key_tap_visibility") val descriptionKeyTapVisibility: String = "",
)