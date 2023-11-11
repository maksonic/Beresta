package ru.maksonic.beresta.feature.tags_list.data.tags

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.common.core.json.JsonConverter
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.usecase.FetchAppLangUseCase
import ru.maksonic.beresta.feature.tags_list.domain.NoteDefaultTagDomain

/**
 * @Author maksonic on 05.11.2023
 */
class TagsLangStore(
    jsonConverter: JsonConverter,
    private val json: Json,
    private val fetchAppLangUseCase: FetchAppLangUseCase,
) {
    private companion object {
        private const val FILE_NAME = "note_tags.json"
    }

    private val convertTagsResult: Result<TagsLangContainer> =
        jsonConverter.convertFileToString(FILE_NAME).fold(
            onSuccess = { Result.success(json.decodeFromString(it)) },
            onFailure = { Result.failure(it) }
        )

    fun fetchTags(): Flow<Result<List<NoteDefaultTagDomain>>> = flow {
        convertTagsResult.fold(
            onSuccess = { container ->
                fetchAppLangUseCase().collect { currentLang ->
                    val data = when (currentLang) {
                        AppLangDomain.RUSSIAN -> container.russian.toDomain()
                        AppLangDomain.ENGLISH -> container.english.toDomain()
                        AppLangDomain.CHINESE -> container.chinese.toDomain()
                        AppLangDomain.CHINESE_TR -> container.chineseTr.toDomain()
                    }

                    emit(Result.success(data))
                }
            },
            onFailure = { emit(Result.failure(it)) })
    }
}