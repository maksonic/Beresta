package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 07.03.2023
 */
@Serializable
data class LangEditorData(
    @SerializedName("hint_input_title")
    val hintInputTitle: String = "",
    @SerializedName("hint_input_message")
    val hintInputMessage: String = "",
    @SerializedName("snack_message_note_updated")
    val hintSnackNoteUpdated: String = "",
    @SerializedName("message_max_note_length_warning")
    val noteMaxLengthWarning: String = "",
    @SerializedName("message_note_wallpaper_is_not_selected")
    val noteWallpaperIsNotSelectedWarning: String = "",
    @SerializedName("top_bar_select_note_wallpaper")
    val topBarTitleSelectNoteWallpaper: String = "",

    @SerializedName("note_wallpaper_category_new")
    val noteWallpaperCategoryNew: String = "",
    @SerializedName("note_wallpaper_category_soft_colors")
    val noteWallpaperCategorySoftColors: String = "",
    @SerializedName("note_wallpaper_category_bright_colors")
    val noteWallpaperCategoryBrightColors: String = "",
    @SerializedName("note_wallpaper_category_gradients")
    val noteWallpaperCategoryGradients: String = "",
    @SerializedName("note_wallpaper_category_grids")
    val noteWallpaperCategoryGrids: String = "",
    @SerializedName("note_wallpaper_category_best")
    val noteWallpaperCategoryBest: String = "",
)