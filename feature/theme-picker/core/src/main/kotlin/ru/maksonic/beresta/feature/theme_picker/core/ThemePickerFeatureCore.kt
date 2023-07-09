package ru.maksonic.beresta.feature.theme_picker.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 19.06.2023
 */
class ThemePickerFeatureCore(private val datastore: Datastore) : ThemePickerApi.Feature.Theme {
    private val themeKey = stringPreferencesKey("prefs_app_theme_key")
    private val darkModeKey = booleanPreferencesKey("prefs_app_theme_dark_mode_key")

    override val current: Flow<Pair<AppTheme, Boolean>> = datastore.datastore.data.map { theme ->
        Pair(
            first = AppTheme.valueOf(theme[themeKey] ?: AppTheme.SYSTEM.name),
            second = theme[darkModeKey] ?: false
        )
    }

    override suspend fun setTheme(theme: AppTheme) {
        datastore.datastore.edit { preferences -> preferences[themeKey] = theme.name }
    }

    override suspend fun updateDarkMode(isDarkMode: Boolean) {
        datastore.datastore.edit { preferences -> preferences[darkModeKey] = isDarkMode }
    }
}