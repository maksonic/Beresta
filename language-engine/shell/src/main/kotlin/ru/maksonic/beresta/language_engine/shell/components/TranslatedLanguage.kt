package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 20.02.2023
 */
@Serializable
data class TranslatedLanguage(
    @SerializedName("russian") val russian: String = "",
    @SerializedName("english") val english: String = "",
    @SerializedName("chinese") val chinese: String = "",
    @SerializedName("chinese_tr") val chineseTr: String = "",
)