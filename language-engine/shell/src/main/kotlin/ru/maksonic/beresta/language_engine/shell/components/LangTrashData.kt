package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 31.05.2023
 */
@Serializable
data class LangTrashData(
    @SerializedName("top_bar_title")
    val topBarTitleTrash: String = "",
    @SerializedName("title_trashed_folders")
    val topBarTitleTrashedFolders: String = "",
    @SerializedName("dialog_title_delete_note")
    val titleDialogDeleteNote: String = "",
    @SerializedName("dialog_title_delete_notes_list")
    val titleDialogDeleteNotesList: String = "",
    @SerializedName("dialog_body_delete_note")
    val dialogBodyDeleteNote: String = "",
    @SerializedName("dialog_body_delete_notes_list")
    val dialogBodyDeleteNotesList: String = "",
    @SerializedName("message_empty_notes_list")
    val messageEmptyTrashNotesList: String = "",
    @SerializedName("dialog_title_delete_folder")
    val titleDialogDeleteFolder: String = "",
    @SerializedName("dialog_title_delete_folders_list")
    val titleDialogDeleteFoldersList: String = "",
    @SerializedName("dialog_body_delete_folder")
    val dialogBodyDeleteFolder: String = "",
    @SerializedName("dialog_body_delete_folders_list")
    val dialogBodyDeleteFoldersList: String = "",
    @SerializedName("message_empty_folders_list")
    val messageEmptyTrashFoldersList: String = "",
    @SerializedName("hint_moved_to_trash_date_prefix")
    val hintRemovedDatePrefix: String = "",
)