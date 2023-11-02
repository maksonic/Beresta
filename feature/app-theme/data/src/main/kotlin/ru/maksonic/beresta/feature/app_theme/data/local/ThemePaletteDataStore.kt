package ru.maksonic.beresta.feature.app_theme.data.local

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeDomain
import ru.maksonic.beresta.feature.app_theme.domain.AppThemePaletteDomain
import ru.maksonic.beresta.feature.app_theme.domain.ThemePaletteContainer

/**
 * @Author maksonic on 19.06.2023
 */

class ThemePaletteDataStore(private val datastore: Datastore) {
    companion object {
        private const val BASE_KEY = "prefs_app_theme_"
    }

    private val lightPaletteKey = stringPreferencesKey(BASE_KEY + "light_palette_key")
    private val darkPaletteKey = stringPreferencesKey(BASE_KEY + "dark_palette_key")
    private val highContrastPaletteKey = stringPreferencesKey(BASE_KEY + "contrast_palette_key")

    val current: Flow<ThemePaletteContainer> = datastore.datastore.data.map { palette ->
        ThemePaletteContainer(
            light = AppThemePaletteDomain.valueOf(
                value = palette[lightPaletteKey] ?: ThemePaletteContainer.Default.light.name
            ),
            night = AppThemePaletteDomain.valueOf(
                value = palette[darkPaletteKey] ?: ThemePaletteContainer.Default.night.name
            ),
            dark = AppThemePaletteDomain.valueOf(
                value = palette[highContrastPaletteKey] ?: ThemePaletteContainer.Default.dark.name
            )
        )
    }

    suspend fun setPalette(
        currentTheme: AppThemeDomain,
        palette: AppThemePaletteDomain,
        isDarkMode: Boolean
    ) {
        datastore.datastore.edit { preferences ->
            val key = when (currentTheme) {
                AppThemeDomain.SYSTEM -> if (isDarkMode) darkPaletteKey else lightPaletteKey
                AppThemeDomain.DAY -> lightPaletteKey
                AppThemeDomain.NIGHT -> darkPaletteKey
                AppThemeDomain.DARK -> highContrastPaletteKey
            }
            preferences[key] = palette.name
        }
    }
}