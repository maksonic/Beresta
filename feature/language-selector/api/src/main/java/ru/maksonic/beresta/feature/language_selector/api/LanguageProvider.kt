package ru.maksonic.beresta.feature.language_selector.api

/**
 * @Author maksonic on 17.02.2023
 */
interface LanguageProvider {
    fun provideLanguage(currentLanguage: AppLanguage): AppLanguage
}