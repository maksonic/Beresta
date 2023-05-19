package ru.maksonic.beresta.feature.theme_picker.core.ui

import ru.maksonic.beresta.feature.theme_picker.api.ThemeUiModel
import ru.maksonic.beresta.feature.theme_picker.core.data.ThemeRepository
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette

/**
 * @Author maksonic on 28.02.2023
 */
data class FeatureState(
    val themes: ThemeUiModel.Collection,
    val palettes: ThemeUiModel.Palette.Collection,
    val isDarkTheme: Boolean,
    val currentTheme: AppTheme,
    val currentLightPalette: AppThemePalette,
    val currentDarkPalette: AppThemePalette,
) {
    companion object {
        val Initial = FeatureState(
            themes = ThemeRepository.themes,
            palettes = ThemeRepository.palettes,
            isDarkTheme = false,
            currentTheme = AppTheme.SYSTEM,
            currentLightPalette = AppThemePalette.BLUE,
            currentDarkPalette = AppThemePalette.BLUE,
        )
    }
}