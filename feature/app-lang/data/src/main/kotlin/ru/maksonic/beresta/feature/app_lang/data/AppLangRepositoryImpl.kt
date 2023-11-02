package ru.maksonic.beresta.feature.app_lang.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.app_lang.data.local.LanguageDataStore
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.LanguageDomain

/**
 * @Author maksonic on 15.10.2023
 */
class AppLangRepositoryImpl(private val languageDataStore: LanguageDataStore) : AppLangRepository {
    override fun fetchLanguagesList(): List<LanguageDomain> = languageDataStore.languagesList

    override fun fetchAppCurrentLang(): Flow<AppLangDomain> = languageDataStore.current
    override suspend fun setLanguage(lang: AppLangDomain) = languageDataStore.setLanguage(lang)
}