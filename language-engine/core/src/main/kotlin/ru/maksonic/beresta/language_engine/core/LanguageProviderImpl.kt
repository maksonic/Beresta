package ru.maksonic.beresta.language_engine.core

import kotlinx.coroutines.flow.flow
import ru.maksonic.beresta.language_engine.shell.LanguageStore
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.BerestaLanguage
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageProviderImpl(
    private val langConverter: LanguageJsonToDataConverter
) : LanguageProvider {

    override suspend fun provideLanguage(currentLanguage: AppLanguage) = flow {
        langConverter.getLangDataFromJson().collect { store ->
            store.onSuccess { data ->
                val berestaLanguage = BerestaLanguage(
                    langTitle = langTitle(currentLanguage, data),
                    translatedLanguage = translated(currentLanguage, data),
                    onboarding = onboardingText(currentLanguage, data),
                    shared = shared(currentLanguage, data),
                    settings = settingsText(currentLanguage, data),
                    settingsAppearance = settingsAppearanceText(currentLanguage, data),
                    settingsSecurity = settingsSecurityText(currentLanguage, data),
                    editNote = editNotesText(currentLanguage, data),
                    folders = folders(currentLanguage, data),
                    trash = trash(currentLanguage, data),
                    sortSheet = sortNotesSheet(currentLanguage, data),
                    hiddenNotes = hiddenNotes(currentLanguage, data)
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

    private fun editNotesText(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.editor
        AppLanguage.ENGLISH -> data.english.editor
        AppLanguage.CHINESE -> data.chinese.editor
        AppLanguage.CHINESE_TR -> data.chineseTr.editor
    }

    private fun settingsText(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.settings
        AppLanguage.ENGLISH -> data.english.settings
        AppLanguage.CHINESE -> data.chinese.settings
        AppLanguage.CHINESE_TR -> data.chineseTr.settings
    }

    private fun settingsAppearanceText(language: AppLanguage, data: LanguageStore) =
        when (language) {
            AppLanguage.RUSSIAN -> data.russian.settingsAppearance
            AppLanguage.ENGLISH -> data.english.settingsAppearance
            AppLanguage.CHINESE -> data.chinese.settingsAppearance
            AppLanguage.CHINESE_TR -> data.chineseTr.settingsAppearance
        }

    private fun settingsSecurityText(language: AppLanguage, data: LanguageStore) =
        when (language) {
            AppLanguage.RUSSIAN -> data.russian.settingsSecurity
            AppLanguage.ENGLISH -> data.english.settingsSecurity
            AppLanguage.CHINESE -> data.chinese.settingsSecurity
            AppLanguage.CHINESE_TR -> data.chineseTr.settingsSecurity
        }

    private fun shared(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.shared
        AppLanguage.ENGLISH -> data.english.shared
        AppLanguage.CHINESE -> data.chinese.shared
        AppLanguage.CHINESE_TR -> data.chineseTr.shared
    }

    private fun folders(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.folders
        AppLanguage.ENGLISH -> data.english.folders
        AppLanguage.CHINESE -> data.chinese.folders
        AppLanguage.CHINESE_TR -> data.chineseTr.folders
    }

    private fun trash(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.trash
        AppLanguage.ENGLISH -> data.english.trash
        AppLanguage.CHINESE -> data.chinese.trash
        AppLanguage.CHINESE_TR -> data.chineseTr.trash
    }

    private fun sortNotesSheet(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.sortNotesSheet
        AppLanguage.ENGLISH -> data.english.sortNotesSheet
        AppLanguage.CHINESE -> data.chinese.sortNotesSheet
        AppLanguage.CHINESE_TR -> data.chineseTr.sortNotesSheet
    }

    private fun hiddenNotes(language: AppLanguage, data: LanguageStore) = when (language) {
        AppLanguage.RUSSIAN -> data.russian.hiddenNotes
        AppLanguage.ENGLISH -> data.english.hiddenNotes
        AppLanguage.CHINESE -> data.chinese.hiddenNotes
        AppLanguage.CHINESE_TR -> data.chineseTr.hiddenNotes
    }
}