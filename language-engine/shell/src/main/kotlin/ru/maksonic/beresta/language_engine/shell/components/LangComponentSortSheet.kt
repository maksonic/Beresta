package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 04.06.2023
 */
@Serializable
data class LangComponentSortSheet(
    @SerialName("title_sheet_notes") val titleSheetNotes: String,
    @SerialName("title_sheet_folders") val titleSheetFolders: String,
    @SerialName("hint_sort_by_ascending") val hintSortByAscending: String,
    @SerialName("hint_sort_by_descending") val hintSortByDescending: String,

    @SerialName("hint_sort_category_alphabet") val hintSortCategoryAlphabet: String,
    @SerialName("hint_sort_category_date_creation") val hintSortCategoryDateCreation: String,
    @SerialName("hint_sort_category_date_update") val hintSortCategoryDateUpdate: String,

    @SerialName("hint_checkbox_sort_pinned_notes") val hintCheckboxPinnedNotes: String,
    @SerialName("hint_checkbox_sort_pinned_folders") val hintCheckboxPinnedFolders: String
) {
    companion object {
        val Default = LangComponentSortSheet(
            titleSheetNotes = "Sort notes",
            titleSheetFolders = "Sort folders",
            hintSortByAscending = "Ascending",
            hintSortByDescending = "Descending",
            hintSortCategoryAlphabet = "Alphabet",
            hintSortCategoryDateCreation = "Date of creation",
            hintSortCategoryDateUpdate = "Date of change",
            hintCheckboxPinnedNotes = "Consider pinned notes",
            hintCheckboxPinnedFolders = "Consider pinned folders"
        )
    }
}