package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 03.04.2023
 */
@Serializable
data class LangComponentsFolders(
    @SerialName("top_bar_title") val topBarTitle: String,
    @SerialName("dialog_title_create_new_folder") val titleDialogNewFolder: String,
    @SerialName("dialog_title_edit_folder") val titleDialogEditFolder: String,
    @SerialName("title_pinned_start_notes_folder") val titlePinnedStartFolder: String,
    @SerialName("title_pinned_end_notes_folder") val titlePinnedEndFolder: String,
    @SerialName("hint_error_empty_folder_name") val hintErrorEmptyFolderName: String,
    @SerialName("hint_removed_folders_count") val hintRemovedFoldersCount: String,
    @SerialName("btn_title_crete_folder") val btnTitleCreateNewFolder: String,
    @SerialName("btn_title_select_folder") val btnTitleSelectFolder: String
) {
    companion object {
        val Default = LangComponentsFolders(
            topBarTitle = "Folder Management",
            titleDialogNewFolder = "New folder",
            titleDialogEditFolder = "Edit folder",
            titlePinnedStartFolder = "All",
            titlePinnedEndFolder = "Uncategorized",
            hintErrorEmptyFolderName = "Folder name cannot be empty.",
            hintRemovedFoldersCount = "Removed folders:",
            btnTitleCreateNewFolder = "Create new folder",
            btnTitleSelectFolder = "Select folder"
        )
    }
}