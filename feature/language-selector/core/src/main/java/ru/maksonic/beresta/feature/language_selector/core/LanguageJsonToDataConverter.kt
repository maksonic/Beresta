package ru.maksonic.beresta.feature.language_selector.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.core.converter.JsonConverter
import ru.maksonic.beresta.feature.language_selector.api.BerestaLanguage

/**
 * @Author maksonic on 16.02.2023
 */
class LanguageJsonToDataConverter(
    private val json: Json,
    private val jsonConverter: JsonConverter,
    private val dispatcher: CoroutineDispatcher
) {
    private companion object {
        private const val FILE_NAME = "language.json"
    }

    fun getLangDataFromJson() = flow {
        val conversion = jsonConverter.convertAssertJsonToString(FILE_NAME)
        conversion.onSuccess { rawString ->
            val languages: BerestaLanguage = json.decodeFromString(rawString)
            emit(languages)
        }
    }.flowOn(dispatcher)
}
