package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 19.02.2023
 */
@Serializable
data class LangSharedData(
    @SerializedName("btn_title_save") val btnTitleSave: String = "",
    @SerializedName("btn_title_cancel") val btnTitleCancel: String = "",
    @SerializedName("btn_title_close") val btnTitleClose: String = "",
    @SerializedName("btn_title_hide") val btnTitleHide: String = "",
    @SerializedName("btn_title_pin") val btnTitlePin: String = "",
    @SerializedName("btn_title_unpin") val btnTitleUnpin: String = "",
    @SerializedName("btn_title_replace") val btnTitleReplace: String = "",
    @SerializedName("btn_title_remove") val btnTitleRemove: String = "",
    @SerializedName("btn_title_create") val btnTitleCreate: String = "",
    @SerializedName("hint_find_note_search_bar") val hintFindNote: String = "",
    @SerializedName("hint_selected_notes_count_bottom_bar") val hintSelectedNotesCount: String = "",
    @SerializedName("dialog_title_create_new_folder") val titleNewFolder: String = "",
    @SerializedName("title_pinned_notes_folder") val titlePinnedChipFilter: String = ""
)