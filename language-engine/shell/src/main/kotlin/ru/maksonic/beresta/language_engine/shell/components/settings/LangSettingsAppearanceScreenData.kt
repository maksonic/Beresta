package ru.maksonic.beresta.language_engine.shell.components.settings

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 07.07.2023
 */
@Serializable
data class LangSettingsAppearanceScreenData(
    @SerializedName("top_bar_title") val topBarTitle: String = "",
    @SerializedName("title_note_card") val titleNoteCard: String = "",
    @SerializedName("item_note_card_shape") val itemNoteCardShape: String = "",
    @SerializedName("hint_note_card_rounded_shape") val hintNoteCardRoundedShape: String = "",
    @SerializedName("hint_note_card_squared_shape") val hintNoteCardSquaredShape: String = "",
    @SerializedName("item_note_card_elevation") val itemNoteCardElevation: String = "",
    @SerializedName("item_note_card_max_lines") val itemNoteCardMaxLines: String = "",
)