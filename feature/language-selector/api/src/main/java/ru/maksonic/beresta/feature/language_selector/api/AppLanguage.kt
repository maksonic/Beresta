package ru.maksonic.beresta.feature.language_selector.api

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
/*
@Serializable(with = LanguageSerializer::class)
data class AppLanguage(
    @SerialName("russian") val russian: Russian,
    @SerialName("english") val english: English,
    @SerialName("chines") val chines: Chines
)

@Serializable
@SerialName("AppLanguage")
private data class LanguageSurrogate(val russian: Russian, val english: English, val chines: Chines)

object LanguageSerializer : KSerializer<AppLanguage> {
    override fun deserialize(decoder: Decoder): AppLanguage {
        val surrogate = decoder.decodeSerializableValue(LanguageSurrogate.serializer())
        return AppLanguage(
            russian = surrogate.russian,
            english = surrogate.english,
            chines = surrogate.chines
        )
    }

    override val descriptor: SerialDescriptor = LanguageSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: AppLanguage) {
        val surrogate = LanguageSurrogate(value.russian, value.english, value.chines)
        encoder.encodeSerializableValue(LanguageSurrogate.serializer(), surrogate)
    }
}*/