package ru.maksonic.beresta.feature.theme_picker.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 19.06.2023
 */
class PalettePickerFeatureCore(private val datastore: Datastore) : ThemePickerApi.Feature.Palette {
    private val lightPaletteKey = stringPreferencesKey("prefs_app_theme_light_palette_key")
    private val darkPaletteKey = stringPreferencesKey("prefs_app_theme_dark_palette_key")
    private val highContrastPaletteKey =
        stringPreferencesKey("prefs_app_theme_contrast_palette_key")

    override val current: Flow<PaletteStore> = datastore.datastore.data.map { palette ->
        PaletteStore(
            light = AppThemePalette.valueOf(
                value = palette[lightPaletteKey] ?: PaletteStore.Default.light.name
            ),
            dark = AppThemePalette.valueOf(
                value = palette[darkPaletteKey] ?: PaletteStore.Default.dark.name
            ),
            highContrast = AppThemePalette.valueOf(
                value = palette[highContrastPaletteKey] ?: PaletteStore.Default.highContrast.name
            )
        )
    }

    override suspend fun setPalette(
        currentTheme: AppTheme,
        isDarkMode: Boolean,
        palette: AppThemePalette
    ) {
        datastore.datastore.edit { preferences ->
            val key = when (currentTheme) {
                AppTheme.SYSTEM -> if (isDarkMode) darkPaletteKey else lightPaletteKey
                AppTheme.LIGHT -> lightPaletteKey
                AppTheme.DARK -> darkPaletteKey
                AppTheme.HIGH_CONTRAST -> highContrastPaletteKey
            }
            preferences[key] = palette.name
        }
    }
}