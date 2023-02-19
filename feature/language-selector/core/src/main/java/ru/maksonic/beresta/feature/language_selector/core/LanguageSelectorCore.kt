package ru.maksonic.beresta.feature.language_selector.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageSelectorCore(private val datastore: Datastore) : LanguageSelectorApi.Lang {
    private val key = stringPreferencesKey("prefs_app_language_key")

    override suspend fun setLanguage(lang: AppLanguage) {
        datastore.datastore.edit { preferences ->
            preferences[key] = lang.name
        }
    }

    override val currentLanguage: Flow<AppLanguage> = datastore.datastore.data.map { langSetting ->
        AppLanguage.valueOf(langSetting[key] ?: AppLanguage.RUSSIAN.name)
    }

    override val languagesTitle: Map<AppLanguage, String> = mapOf(
        AppLanguage.RUSSIAN to "\uD83C\uDDF7\uD83C\uDDFA  Русский",
        AppLanguage.ENGLISH to "\uD83C\uDDEC\uD83C\uDDE7  English",
        AppLanguage.CHINES to "\uD83C\uDDE8\uD83C\uDDF3  中国人"
    )
}