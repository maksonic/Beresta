package ru.maksonic.beresta.feature.language_selector.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.maksonic.beresta.feature.language_selector.api.languages.Chines
import ru.maksonic.beresta.feature.language_selector.api.languages.English
import ru.maksonic.beresta.feature.language_selector.api.languages.Russian

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class LanguageStore(
    @SerializedName("russian") val russian: Russian,
    @SerializedName("english") val english: English,
    @SerializedName("chines") val chines: Chines
)