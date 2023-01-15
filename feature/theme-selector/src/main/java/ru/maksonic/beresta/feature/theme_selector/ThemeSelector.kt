package ru.maksonic.beresta.feature.theme_selector

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 15.12.2022
 */
interface ThemeSelector {
    suspend fun setTheme(theme: AppTheme)
    val currentSavedTheme: Flow<AppTheme>

    class Feature(private val datastore: Datastore) : ThemeSelector {
        private val key = stringPreferencesKey("prefs_app_theme_key")
        private val currentTheme = MutableStateFlow(AppTheme.SYSTEM)

        override suspend fun setTheme(theme: AppTheme) {
            datastore.datastore.edit { preferences ->
                preferences[key] = theme.name
            }
        }

        override val currentSavedTheme: Flow<AppTheme> = flow {
            datastore.datastore.data.map { preferences ->
                preferences[key] ?: AppTheme.SYSTEM.name
            }.map {
                currentTheme.value = AppTheme.valueOf(it)
            }
            emit(currentTheme.value)
        }
        }
    /*override fun readTheme(): Flow<AppTheme> {
            datastore.datastore.data.map { preferences ->
                preferences[key] ?: AppTheme.SYSTEM.name
            }.map {
                currentTheme.value = AppTheme.valueOf(it)
            }
            return currentTheme
        }*/
    }