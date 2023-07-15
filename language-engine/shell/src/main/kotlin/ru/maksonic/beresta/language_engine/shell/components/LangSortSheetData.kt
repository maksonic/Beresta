package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 04.06.2023
 */
@Serializable
data class LangSortSheetData(
    @SerializedName("title_sheet_notes") val titleSheetNotes: String = "",
    @SerializedName("title_sheet_folders") val titleSheetFolders: String = "",

    @SerializedName("hint_sort_by_ascending") val hintSortByAscending: String = "",
    @SerializedName("hint_sort_by_descending") val hintSortByDescending: String = "",

    @SerializedName("hint_sort_category_alphabet") val hintSortCategoryAlphabet: String = "",
    @SerializedName("hint_sort_category_date_creation") val hintSortCategoryDateCreation: String = "",
    @SerializedName("hint_sort_category_date_update") val hintSortCategoryDateUpdate: String = "",

    @SerializedName("hint_checkbox_sort_pinned_notes") val hintCheckboxPinnedNotes: String = "",
    @SerializedName("hint_checkbox_sort_pinned_folders") val hintCheckboxPinnedFolders: String = "",

)