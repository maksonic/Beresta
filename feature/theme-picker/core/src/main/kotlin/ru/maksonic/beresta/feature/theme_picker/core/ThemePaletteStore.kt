package ru.maksonic.beresta.feature.theme_picker.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 24.04.2023
 */
class ThemePaletteStore(private val datastore: Datastore) : ThemePickerApi.Palette {
    private val lightPaletteKey = stringPreferencesKey("prefs_app_theme_light_palette_key")
    private val darkPaletteKey = stringPreferencesKey("prefs_app_theme_dark_palette_key")

    override val current: Flow<PaletteStore> = datastore.datastore.data.map { palette ->
        PaletteStore(
            light = AppThemePalette.valueOf(
                value = palette[lightPaletteKey] ?: PaletteStore.Default.light.name
            ),
            dark = AppThemePalette.valueOf(
                value = palette[darkPaletteKey] ?: PaletteStore.Default.dark.name
            )
        )
    }

    override suspend fun setPalette(isDarkTheme: Boolean, palette: AppThemePalette) {
        datastore.datastore.edit { preferences ->
            val key = if (isDarkTheme) darkPaletteKey else lightPaletteKey
            preferences[key] = palette.name
        }
    }
}