package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 19.02.2023
 */
@Serializable
data class LangComponentShared(
    @SerialName("note") val note: String,
    @SerialName("note_title") val title: String,
    @SerialName("btn_title_accept") val btnTitleAccept: String,
    @SerialName("btn_title_back") val btnTitleBack: String,
    @SerialName("btn_title_by_default") val btnTitleByDefault: String,
    @SerialName("btn_title_cancel") val btnTitleCancel: String,
    @SerialName("btn_title_close") val btnTitleClose: String,
    @SerialName("btn_title_create") val btnTitleCreate: String,
    @SerialName("btn_title_delete") val btnTitleDelete: String,
    @SerialName("btn_title_edit") val btnTitleEdit: String,
    @SerialName("btn_title_hide") val btnTitleHide: String,
    @SerialName("btn_title_next") val btnTitleNext: String,
    @SerialName("btn_title_pin") val btnTitlePin: String,
    @SerialName("btn_title_remove") val btnTitleRemove: String,
    @SerialName("btn_title_replace") val btnTitleReplace: String,
    @SerialName("btn_title_restore") val btnTitleRestore: String,
    @SerialName("btn_title_retry") val btnTitleRetry: String,
    @SerialName("btn_title_save") val btnTitleSave: String,
    @SerialName("btn_title_skip_sync") val btnTitleSkipSync: String,
    @SerialName("btn_title_sync") val btnTitleSync: String,
    @SerialName("btn_title_unhide") val btnTitleUnhide: String,
    @SerialName("btn_title_unpin") val btnTitleUnpin: String,
    @SerialName("hint_find_note_search_bar") val hintFindNote: String,
    @SerialName("hint_selected_items_count") val hintSelectedItemsCount: String,
    @SerialName("hint_removed_notes_count") val hintRemovedNotesCount: String,
    @SerialName("hint_no_folders") val hintNoFolders: String,
    @SerialName("hint_no_notes") val hintNoNotes: String,
    @SerialName("hint_nothing_found") val hintNothingFound: String,
    @SerialName("hint_an_error_has_occurred") val hintAnErrorHasOccurred: String,
    @SerialName("note_title_placeholder") val noteTitlePlaceholder: String,
    @SerialName("note_message_placeholder") val noteMessagePlaceholder: String
) {
    companion object {
        val Default = LangComponentShared(
            note = "Note",
            title = "Title",
            btnTitleAccept = "Ok",
            btnTitleBack = "Back",
            btnTitleByDefault = "Default",
            btnTitleCancel = "Cancel",
            btnTitleClose = "Close",
            btnTitleCreate = "Create",
            btnTitleDelete = "Delete",
            btnTitleEdit = "Edit",
            btnTitleHide = "Hide",
            btnTitleNext = "Next",
            btnTitlePin = "Pin",
            btnTitleRemove = "Remove",
            btnTitleReplace = "Replace",
            btnTitleRestore = "Restore",
            btnTitleRetry = "Retry",
            btnTitleSave = "Save",
            btnTitleSkipSync = "Not now",
            btnTitleSync = "Synchronize",
            btnTitleUnhide = "Unhide",
            btnTitleUnpin = "Unpin",
            hintFindNote = "Find note",
            hintSelectedItemsCount = "Selected: ",
            hintRemovedNotesCount = "Removed notes:",
            hintNoFolders = "No folders",
            hintNoNotes = "No notes",
            hintNothingFound = "Nothing found",
            hintAnErrorHasOccurred = "An error has occurred",
            noteTitlePlaceholder = "Note title placeholder",
            noteMessagePlaceholder = "Note message placeholder"
        )
    }
}