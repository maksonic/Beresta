package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 19.02.2023
 */
@Serializable
data class LangSharedData(
    @SerializedName("btn_title_save") val btnTitleSave: String = "",
    @SerializedName("btn_title_cancel") val btnTitleCancel: String = "",
    @SerializedName("btn_title_close") val btnTitleClose: String = "",
    @SerializedName("hint_find_note_search_bar") val hintFindNote: String = "",
)