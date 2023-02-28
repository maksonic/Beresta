package ru.maksonic.beresta.feature.theme_selector.core.presentation

import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.feature.theme_selector.core.data.Palettes
import ru.maksonic.beresta.feature.theme_selector.core.data.ThemeRepository
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette

/**
 * @Author maksonic on 28.02.2023
 */
data class FeatureState(
    val themes: ThemesCollection = ThemeRepository.themes,
    val palettes: Palettes = ThemeRepository.palettes,
    val isDarkTheme: Boolean = false,
    val currentTheme: AppTheme = AppTheme.SYSTEM,
    val currentLightPalette: AppThemePalette = AppThemePalette.BLUE,
    val currentDarkPalette: AppThemePalette = AppThemePalette.BLUE,
)