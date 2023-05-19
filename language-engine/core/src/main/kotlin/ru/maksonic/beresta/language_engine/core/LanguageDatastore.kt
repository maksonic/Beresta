package ru.maksonic.beresta.language_engine.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 22.04.2023
 */
class LanguageDatastore(private val datastore: Datastore) : LanguageEngineApi {
    private val key = stringPreferencesKey("prefs_app_language_key")

    override suspend fun setLanguage(lang: AppLanguage) {
        datastore.datastore.edit { preferences ->
            preferences[key] = lang.name
        }
    }

    override val current: Flow<AppLanguage> = datastore.datastore.data.map { langSetting ->
        AppLanguage.valueOf(langSetting[key] ?: AppLanguage.RUSSIAN.name)
    }
}