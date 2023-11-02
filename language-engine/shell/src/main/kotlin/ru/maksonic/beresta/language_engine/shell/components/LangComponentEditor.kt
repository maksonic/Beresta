package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 07.03.2023
 */
@Serializable
data class LangComponentEditor(
    @SerialName("hint_input_title") val hintInputTitle: String,
    @SerialName("hint_input_message") val hintInputMessage: String,
    @SerialName("snack_message_note_updated") val hintSnackNoteUpdated: String,
    @SerialName("message_max_note_length_warning") val noteMaxLengthWarning: String,
    @SerialName("message_note_wallpaper_is_not_selected") val noteWallpaperIsNotSelectedWarning: String,
    @SerialName("top_bar_select_note_wallpaper") val topBarTitleSelectNoteWallpaper: String,

    @SerialName("note_wallpaper_category_color") val noteWallpaperCategoryColor: String,
    @SerialName("note_wallpaper_category_gradient") val noteWallpaperCategoryGradient: String,
    @SerialName("note_wallpaper_category_texture") val noteWallpaperCategoryTexture: String,
    @SerialName("note_wallpaper_category_image") val noteWallpaperCategoryImage: String,
    @SerialName("hint_wallpaper_picker_background") val hintWallpaperPickerBackground: String,
    @SerialName("hint_wallpaper_picker_style") val hintWallpaperPickerStyle: String,

    @SerialName("dialog_title_marker_color_picker") val dialogTitleMarkerColorPicker: String,
    @SerialName("dialog_title_wallpaper_picker") val dialogTitleWallpaperPicker: String,
    @SerialName("dialog_title_wallpaper_texture_style_picker") val dialogTitleWallpaperTextureStylePicker: String,
    @SerialName("tab_title_marker_color_category_bright") val tabTitleMarkerPickerColorBright: String,
    @SerialName("tab_title_marker_color_category_normal") val tabTitleMarkerPickerColorNormal: String,
    @SerialName("tab_title_marker_color_category_neutral") val tabTitleMarkerPickerColorNeutral: String,
) {
    companion object {
        val Default = LangComponentEditor(
            hintInputTitle = "Title",
            hintInputMessage = "Write:",
            hintSnackNoteUpdated = "Note has been updated",
            noteMaxLengthWarning = "The note is too long. The text at the end was cut off.",
            noteWallpaperIsNotSelectedWarning = "You have not selected a background for your note.",
            topBarTitleSelectNoteWallpaper = "Set note background",
            noteWallpaperCategoryColor = "Color",
            noteWallpaperCategoryGradient = "Gradient",
            noteWallpaperCategoryTexture = "Texture",
            noteWallpaperCategoryImage = "Wallpaper",
            hintWallpaperPickerBackground = "Background",
            hintWallpaperPickerStyle = "Ready style",
            dialogTitleMarkerColorPicker = "Select marker color",
            dialogTitleWallpaperPicker = "Select background",
            dialogTitleWallpaperTextureStylePicker = "Select style",
            tabTitleMarkerPickerColorBright = "Bright",
            tabTitleMarkerPickerColorNormal = "Normal",
            tabTitleMarkerPickerColorNeutral = "Neutral",
        )
    }
}