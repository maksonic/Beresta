package ru.maksonic.beresta.language_engine.core

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi
import ru.maksonic.beresta.language_engine.shell.provider.LanguageModel
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageProviderCore(
    private val langConverter: LanguageJsonToDataConverter
) : LanguageProvider {

    override suspend fun provideLanguage(currentLanguage: AppLangUi) = flow {
        langConverter.getLangDataFromJson()
            .catch { emit(LanguageModel.Default) }
            .collect { result ->
                result.onSuccess { data ->
                    val languageModel = when (currentLanguage) {
                        AppLangUi.RUSSIAN -> LanguageModel.russian(data)
                        AppLangUi.ENGLISH -> LanguageModel.english(data)
                        AppLangUi.CHINESE -> LanguageModel.chinese(data)
                        AppLangUi.CHINESE_TR -> LanguageModel.chineseTr(data)
                    }
                    emit(languageModel)
                }
                result.onFailure { emit(LanguageModel.Default) }
            }
    }
}