package ru.maksonic.beresta.feature.language_selector.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.core.converter.JsonConverter
import ru.maksonic.beresta.feature.language_selector.api.LanguageStore
import java.lang.reflect.Type

/**
 * @Author maksonic on 16.02.2023
 */
class LanguageJsonToDataConverter(
    private val gson: Gson,
    private val jsonConverter: JsonConverter,
    private val dispatcher: CoroutineDispatcher
) {
    private companion object {
        private const val FILE_NAME = "languages.json"
    }

    fun getLangDataFromJson() = flow<Result<LanguageStore>> {
        val conversion = jsonConverter.convertAssertJsonToString(FILE_NAME)
        conversion.onSuccess { rawString ->
            try {
                val type: Type = object : TypeToken<LanguageStore?>() {}.type
                val store: LanguageStore = gson.fromJson(rawString, type)
                emit(Result.success(store))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
        conversion.onFailure { throwable ->
            emit(Result.failure(throwable))
        }
    }.flowOn(dispatcher)
}
