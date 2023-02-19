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
                    onboardingTextData = onboardingText(language, data),
                    languageSheetTextData = languageSheetText(language, data)
                )
                emit(berestaLanguage)
            }
            store.onFailure {
                emit(BerestaLanguage())
            }
        }
    }

    private fun onboardingText(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.onboardingTextData
        AppLanguage.ENGLISH -> data.english.onboardingTextData
        AppLanguage.CHINES -> data.chines.onboardingTextData
    }

    private fun languageSheetText(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.langSheet
        AppLanguage.ENGLISH -> data.english.langSheet
        AppLanguage.CHINES -> data.chines.langSheet
    }

}