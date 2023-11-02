package ru.maksonic.beresta.feature.app_theme.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain

/**
 * @Author maksonic on 19.06.2023
 */
class ThemeDataStore(private val datastore: Datastore) {
    private val themeKey = stringPreferencesKey("prefs_app_theme_key")
    private val darkModeKey = booleanPreferencesKey("prefs_app_theme_dark_mode_key")

    val current: Flow<AppThemeDomain> = datastore.datastore.data.map { theme ->
        AppThemeDomain.valueOf(theme[themeKey] ?: AppThemeDomain.SYSTEM.name)
    }

    val isDarkMode: Flow<Boolean> = datastore.datastore.data.map { theme ->
        theme[darkModeKey] ?: false
    }

    suspend fun setTheme(theme: AppThemeDomain) {
        datastore.datastore.edit { preferences -> preferences[themeKey] = theme.name }
    }

    suspend fun updateDarkMode(isDark: Boolean) {
        datastore.datastore.edit { preferences -> preferences[darkModeKey] = isDark }
    }
}