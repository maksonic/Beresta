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
    @SerializedName("dialog_body_delete_note")
    val dialogBodyDeleteNote: String = "",
)
