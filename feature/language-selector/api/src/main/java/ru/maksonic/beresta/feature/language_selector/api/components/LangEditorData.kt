package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 07.03.2023
 */
@Serializable
data class LangEditorData(
    @SerializedName("hint_input_title") val hintInputTitle: String = "",
    @SerializedName("hint_input_message") val hintInputMessage: String = "",
    @SerializedName("message_max_note_length_warning") val noteMaxLengthWarning: String = ""
)