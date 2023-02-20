package ru.maksonic.beresta.feature.language_selector.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.feature.language_selector.api.languages.Chinese
import ru.maksonic.beresta.feature.language_selector.api.languages.English
import ru.maksonic.beresta.feature.language_selector.api.languages.Russian

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class LanguageStore(
    @SerializedName("russian") val russian: Russian,
    @SerializedName("english") val english: English,
    @SerializedName("chinese") val chinese: Chinese
)