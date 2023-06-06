package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 04.06.2023
 */
@Serializable
data class LangSortNotesSheetData(
    @SerializedName("title_sheet")
    val titleSheet: String = "",

    @SerializedName("hint_sort_category_alphabet")
    val hintSortCategoryAlphabet: String = "",
    @SerializedName("hint_sort_category_date_creation")
    val hintSortCategoryDateCreation: String = "",
    @SerializedName("hint_sort_category_date_update")
    val hintSortCategoryDateUpdate: String = "",
    @SerializedName("item_in_alphabet")
    val itemInAlphabet: String = "",
    @SerializedName("item_in_reverse_alphabet")
    val itemInReverseAlphabet: String = "",
    @SerializedName("item_by_date_creation")
    val itemByDateCreation: String = "",
    @SerializedName("item_by_reverse_date_creation")
    val itemByReverseDateCreation: String = "",
    @SerializedName("item_by_date_update")
    val itemByDateUpdate: String = "",
    @SerializedName("item_by_reverse_date_update")
    val itemByReverseDateUpdate: String = "",
    @SerializedName("hint_checkbox_sort_pinned_notes")
    val hintCheckboxSortPinned: String = "",
)