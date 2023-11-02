package ru.maksonic.beresta.common.ui_theme

/**
 * @Author maksonic on 14.10.2023
 */
data class ThemeUiContainer(
    val currentTheme: AppThemeUi,
    val currentPalette: AppThemePaletteUi,
    val paletteContainer: ThemePalettesUiContainer,
    val isDarkMode: Boolean
)