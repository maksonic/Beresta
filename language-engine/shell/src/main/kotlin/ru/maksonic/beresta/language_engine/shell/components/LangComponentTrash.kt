package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 31.05.2023
 */
@Serializable
data class LangComponentTrash(
    @SerialName("top_bar_title") val topBarTitleTrash: String,
    @SerialName("title_trashed_folders") val topBarTitleTrashedFolders: String,
    @SerialName("dialog_title_delete_note") val titleDialogDeleteNote: String,
    @SerialName("dialog_title_delete_notes_list") val titleDialogDeleteNotesList: String,
    @SerialName("dialog_body_delete_note") val dialogBodyDeleteNote: String,
    @SerialName("dialog_body_delete_notes_list") val dialogBodyDeleteNotesList: String,
    @SerialName("message_empty_notes_list") val messageEmptyTrashNotesList: String,
    @SerialName("dialog_title_delete_folder") val titleDialogDeleteFolder: String,
    @SerialName("dialog_title_delete_folders_list") val titleDialogDeleteFoldersList: String,
    @SerialName("dialog_body_delete_folder") val dialogBodyDeleteFolder: String,
    @SerialName("dialog_body_delete_folders_list") val dialogBodyDeleteFoldersList: String,
    @SerialName("message_empty_folders_list") val messageEmptyTrashFoldersList: String,
    @SerialName("hint_moved_to_trash_date_prefix") val hintRemovedDatePrefix: String,
) {
    companion object {
        val Default = LangComponentTrash(
            topBarTitleTrash = "Trash",
            topBarTitleTrashedFolders = "Removed folders",
            titleDialogDeleteNote = "Delete a note permanently?",
            titleDialogDeleteNotesList = "Delete notes permanently?",
            dialogBodyDeleteNote = "After deleting a note from the trash, it will be impossible to restore it.",
            dialogBodyDeleteNotesList = "Once selected notes are deleted from the trash, they cannot be recovered.",
            messageEmptyTrashNotesList = "No deleted notes",
            titleDialogDeleteFolder = "Delete notes folder permanently?",
            titleDialogDeleteFoldersList = "Delete note folders permanently?",
            dialogBodyDeleteFolder = "After deleting a folder with notes from the trash, it will not be possible to restore it.",
            dialogBodyDeleteFoldersList = "Once the selected notes folders are deleted from the trash, they cannot be restored.",
            messageEmptyTrashFoldersList = "No deleted folders",
            hintRemovedDatePrefix = "removed",
        )
    }
}