package ru.maksonic.beresta.feature.language_selector.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.maksonic.beresta.feature.language_selector.api.LanguageStore
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.provider.BerestaLanguage
import ru.maksonic.beresta.feature.language_selector.api.provider.LanguageProvider

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageProviderImpl(
    private val langConverter: LanguageJsonToDataConverter
) : LanguageProvider {

    override suspend fun provideLanguage(language: AppLanguage): Flow<BerestaLanguage> = flow {
        langConverter.getLangDataFromJson().collect { store ->
            store.onSuccess { data ->
                val berestaLanguage = BerestaLanguage(
                    langTitle = langTitle(language, data),
                    translatedLanguage = translated(language, data),
                    onboarding = onboardingText(language, data),
                    shared = shared(language, data),
                    settings = settingsText(language, data)
                )
                emit(berestaLanguage)
            }
            store.onFailure {
                emit(BerestaLanguage())
            }
        }
    }

    private fun langTitle(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.langTitle
        AppLanguage.ENGLISH -> data.english.langTitle
        AppLanguage.CHINESE -> data.chinese.langTitle
        AppLanguage.CHINESE_TR -> data.chineseTr.langTitle
    }

    private fun translated(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.translatedLanguage
        AppLanguage.ENGLISH -> data.english.translatedLanguage
        AppLanguage.CHINESE -> data.chinese.translatedLanguage
        AppLanguage.CHINESE_TR -> data.chineseTr.translatedLanguage
    }

    private fun onboardingText(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.langOnboardingData
        AppLanguage.ENGLISH -> data.english.langOnboardingData
        AppLanguage.CHINESE -> data.chinese.langOnboardingData
        AppLanguage.CHINESE_TR -> data.chineseTr.langOnboardingData
    }

    private fun settingsText(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.settings
        AppLanguage.ENGLISH -> data.english.settings
        AppLanguage.CHINESE -> data.chinese.settings
        AppLanguage.CHINESE_TR -> data.chineseTr.settings
    }

    private fun shared(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.shared
        AppLanguage.ENGLISH -> data.english.shared
        AppLanguage.CHINESE -> data.chinese.shared
        AppLanguage.CHINESE_TR -> data.chineseTr.shared
    }
}