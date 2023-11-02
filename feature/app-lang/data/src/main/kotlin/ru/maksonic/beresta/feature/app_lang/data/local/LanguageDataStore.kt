package ru.maksonic.beresta.feature.app_lang.data.local

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.LanguageDomain

/**
 * @Author maksonic on 15.10.2023
 */
class LanguageDataStore(private val datastore: Datastore) {
    private val key = stringPreferencesKey("prefs_app_language_key")

    private val languages = listOf(
            LanguageDomain(id = AppLangDomain.RUSSIAN.ordinal, AppLangDomain.RUSSIAN),
            LanguageDomain(id = AppLangDomain.ENGLISH.ordinal, AppLangDomain.ENGLISH),
            LanguageDomain(id = AppLangDomain.CHINESE.ordinal, AppLangDomain.CHINESE),
            LanguageDomain(id = AppLangDomain.CHINESE_TR.ordinal, AppLangDomain.CHINESE_TR),
        )

    val languagesList = languages

    val current: Flow<AppLangDomain> = datastore.datastore.data.map { langSetting ->
        AppLangDomain.valueOf(langSetting[key] ?: AppLangDomain.RUSSIAN.name)
    }

    suspend fun setLanguage(lang: AppLangDomain) {
        datastore.datastore.edit { preferences ->
            preferences[key] = lang.name
        }
    }

}