package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 03.04.2023
 */
@Serializable
data class LangFoldersListData(
    @SerializedName("dialog_title_create_new_folder") val titleNewFolder: String = "",
    @SerializedName("title_pinned_notes_folder") val titlePinnedChipFilter: String = "",
    @SerializedName("hint_error_empty_folder_name") val hintErrorEmptyFolderName: String = "",
    @SerializedName("btn_title_crete_folder") val btnTitleCreateNewFolder: String = "",
    @SerializedName("top_bar_title") val topBarTitle: String = ""
)