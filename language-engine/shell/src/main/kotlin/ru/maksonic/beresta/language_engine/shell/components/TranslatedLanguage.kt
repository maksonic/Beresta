package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 20.02.2023
 */
@Serializable
data class TranslatedLanguage(
    @SerialName("russian") val russian: String,
    @SerialName("english") val english: String,
    @SerialName("chinese") val chinese: String,
    @SerialName("chinese_tr") val chineseTr: String,
) {
    companion object {
        val Default = TranslatedLanguage(
            russian = "Русский",
            english = "English",
            chinese = "简体中文",
            chineseTr = "繁体中文",
        )
    }
}