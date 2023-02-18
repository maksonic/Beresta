package ru.maksonic.beresta.feature.language_selector.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.language_selector.api.AppLanguage
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
}