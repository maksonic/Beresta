package ru.maksonic.beresta.feature.app_lang.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 15.10.2023
 */
interface AppLangRepository {
    fun fetchLanguagesList(): List<LanguageDomain>
    fun fetchAppCurrentLang(): Flow<AppLangDomain>
    suspend fun setLanguage(lang: AppLangDomain)
}