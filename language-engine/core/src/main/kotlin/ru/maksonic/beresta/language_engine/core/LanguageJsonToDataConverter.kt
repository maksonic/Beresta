package ru.maksonic.beresta.language_engine.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.common.core.json.JsonConverter
import ru.maksonic.beresta.language_engine.shell.LanguageContainer

/**
 * @Author maksonic on 16.02.2023
 */
class LanguageJsonToDataConverter(
    private val jsonConverter: JsonConverter,
    private val json: Json,
    private val dispatcher: CoroutineDispatcher
) {
    private companion object {
        private const val FILE_NAME = "languages.json"
    }

    fun getLangDataFromJson() = flow<Result<LanguageContainer>> {
        val rawData = jsonConverter.convertFileToString(FILE_NAME)

        rawData.onSuccess { value -> emit(Result.success(json.decodeFromString(value))) }
        rawData.onFailure { emit(Result.failure(it)) }
    }.flowOn(dispatcher)
}
