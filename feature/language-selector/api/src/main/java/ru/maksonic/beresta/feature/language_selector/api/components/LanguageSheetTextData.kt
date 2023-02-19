package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 19.02.2023
 */
@Serializable
data class LanguageSheetTextData(
    @SerializedName("btn_title_save_language")
    val btnTitleSaveLanguage: String = "",
)