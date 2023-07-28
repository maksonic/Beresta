package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 19.02.2023
 */
@Serializable
data class LangSharedData(
    @SerializedName("note") val note: String = "",
    @SerializedName("note_title") val title: String = "",
    @SerializedName("btn_title_save") val btnTitleSave: String = "",
    @SerializedName("btn_title_cancel") val btnTitleCancel: String = "",
    @SerializedName("btn_title_accept") val btnTitleAccept: String = "",
    @SerializedName("btn_title_close") val btnTitleClose: String = "",
    @SerializedName("btn_title_hide") val btnTitleHide: String = "",
    @SerializedName("btn_title_unhide") val btnTitleUnhide: String = "",
    @SerializedName("btn_title_pin") val btnTitlePin: String = "",
    @SerializedName("btn_title_unpin") val btnTitleUnpin: String = "",
    @SerializedName("btn_title_replace") val btnTitleReplace: String = "",
    @SerializedName("btn_title_remove") val btnTitleRemove: String = "",
    @SerializedName("btn_title_delete") val btnTitleDelete: String = "",
    @SerializedName("btn_title_create") val btnTitleCreate: String = "",
    @SerializedName("btn_title_change") val btnTitleChange: String = "",
    @SerializedName("btn_title_restore") val btnTitleRestore: String = "",
    @SerializedName("btn_title_retry") val btnTitleRetry: String = "",
    @SerializedName("btn_title_by_default") val btnTitleByDefault: String = "",
    @SerializedName("hint_find_note_search_bar") val hintFindNote: String = "",
    @SerializedName("hint_selected_items_count") val hintSelectedItemsCount: String = "",
    @SerializedName("hint_removed_notes_count") val hintRemovedNotesCount: String = "",
    @SerializedName("hint_no_notes") val hintNoNotes: String = "",
    @SerializedName("hint_nothing_found") val hintNothingFound: String = "",
    @SerializedName("hint_an_error_has_occurred") val hintAnErrorHasOccurred: String = "",
    @SerializedName("note_title_placeholder") val noteTitlePlaceholder: String = "",
    @SerializedName("note_message_placeholder") val noteMessagePlaceholder: String = "",
)