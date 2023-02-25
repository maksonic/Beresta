package ru.maksonic.beresta.feature.theme_selector.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette

/**
 * @Author maksonic on 15.02.2023
 */
class ThemeSelectorCore(private val datastore: Datastore) : ThemeSelectorApi.Theme {
    private val themeKey = stringPreferencesKey("prefs_app_theme_key")
    private val paletteKey = stringPreferencesKey("prefs_app_theme_colors_key")

    override suspend fun setTheme(theme: AppTheme) {
        datastore.datastore.edit { preferences ->
            preferences[themeKey] = theme.name
        }
    }

    override val currentTheme: Flow<AppTheme> = datastore.datastore.data.map { theme ->
        AppTheme.valueOf(theme[themeKey] ?: AppTheme.SYSTEM.name)
    }

    override suspend fun setColorPalette(palette: ThemeColorPalette) {
        datastore.datastore.edit { preferences ->
            preferences[paletteKey] = palette.name
        }
    }

    override val currentPalette: Flow<ThemeColorPalette> = datastore.datastore.data.map { palette ->
        ThemeColorPalette.valueOf(palette[paletteKey] ?: ThemeColorPalette.BLUE.name)
    }
}