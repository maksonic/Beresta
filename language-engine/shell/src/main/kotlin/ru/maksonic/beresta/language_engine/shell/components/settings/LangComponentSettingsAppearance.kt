package ru.maksonic.beresta.language_engine.shell.components.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 07.07.2023
 */
@Serializable
data class LangComponentSettingsAppearance(
    @SerialName("top_bar_title") val topBarTitle: String,
    @SerialName("title_note_card") val titleNoteCard: String,
    @SerialName("item_note_card_shape") val itemNoteCardShape: String,
    @SerialName("hint_note_card_rounded_shape") val hintNoteCardRoundedShape: String,
    @SerialName("hint_note_card_squared_shape") val hintNoteCardSquaredShape: String,
    @SerialName("item_note_card_elevation") val itemNoteCardElevation: String,
    @SerialName("item_note_card_max_lines") val itemNoteCardMaxLines: String,
    @SerialName("item_note_card_color_marker") val itemNoteCardColorMarker: String,
    @SerialName("title_animations") val titleAnimations: String,
    @SerialName("item_velocity") val itemAnimVelocity: String,
    @SerialName("hint_animation_velocity_disabled") val hintAnimDisabled: String,
    @SerialName("hint_animation_velocity_slow") val hintAnimSlow: String,
    @SerialName("hint_animation_velocity_normal") val hintAnimNormal: String,
    @SerialName("hint_animation_velocity_fast") val hintAnimFast: String,
    @SerialName("hint_animation_velocity_very_fast") val hintAnimVeryFast: String,
) {
    companion object {
        val Default = LangComponentSettingsAppearance(
            topBarTitle = "Appearance",
            titleNoteCard = "Card",
            itemNoteCardShape = "Corners",
            hintNoteCardRoundedShape = "Round",
            hintNoteCardSquaredShape = "Square",
            itemNoteCardElevation = "Elevation",
            itemNoteCardMaxLines = "Number of lines",
            itemNoteCardColorMarker = "Color indicator",
            titleAnimations = "Animations",
            itemAnimVelocity = "Velocity",
            hintAnimDisabled = "Disabled",
            hintAnimSlow = "Slow",
            hintAnimNormal = "Normal",
            hintAnimFast = "Fast",
            hintAnimVeryFast = "Very fast",
        )
    }
}