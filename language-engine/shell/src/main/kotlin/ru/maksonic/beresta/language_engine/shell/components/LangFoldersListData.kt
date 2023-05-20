package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 03.04.2023
 */
@Serializable
data class LangFoldersListData(
    @SerializedName("dialog_title_create_new_folder") val titleDialogNewFolder: String = "",
    @SerializedName("dialog_title_edit_folder") val titleDialogEditFolder: String = "",
    @SerializedName("title_pinned_start_notes_folder") val titlePinnedStartFolder: String = "",
    @SerializedName("title_pinned_end_notes_folder") val titlePinnedEndFolder: String = "",
    @SerializedName("hint_error_empty_folder_name") val hintErrorEmptyFolderName: String = "",
    @SerializedName("hint_removed_folders_count") val hintRemovedFoldersCount: String = "",
    @SerializedName("btn_title_crete_folder") val btnTitleCreateNewFolder: String = "",
    @SerializedName("top_bar_title") val topBarTitle: String = ""
)