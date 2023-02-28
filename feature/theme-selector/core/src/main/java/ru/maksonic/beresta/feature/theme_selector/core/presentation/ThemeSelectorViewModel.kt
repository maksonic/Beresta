package ru.maksonic.beresta.feature.theme_selector.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.feature.theme_selector.api.SystemThemeCheckerApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemePaletteSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette

/**
 * @Author maksonic on 20.02.2023
 */
class ThemeSelectorViewModel(
    private val themeSelector: ThemeSelectorApi,
    private val paletteSelector: ThemePaletteSelectorApi,
    private val themeChecker: SystemThemeCheckerApi,
) : ViewModel() {

    private val _featureState = MutableStateFlow(FeatureState())
    val model = _featureState.asStateFlow()

    init {
        initFeatureState()
    }

    private fun initFeatureState() {
        viewModelScope.launch {
            val isEnabledDarkMode = themeChecker.isEnabledSystemDarkMode
            val themeState = themeSelector.currentTheme
            val paletteState = paletteSelector.currentPalette

            combine(isEnabledDarkMode, themeState, paletteState) { isDarkTheme, theme, palette ->
                _featureState.update { model ->
                    return@update model.copy(
                        currentTheme = theme,
                        currentLightPalette = palette.light,
                        currentDarkPalette = palette.dark,
                        isDarkTheme = isDarkTheme
                    )
                }
                updateThemeSelection(theme.ordinal)
                updatePaletteSelection()
            }.collect()
        }
    }

    private fun updateThemeSelection(themeOrdinal: Int) {
        _featureState.update { model ->
            val selectedTheme = model.themes.data.map { item ->
                val isSelected = item.theme.ordinal == themeOrdinal
                item.copy(isSelected = isSelected)
            }.toTypedArray()

            return@update model.copy(themes = model.themes.copy(data = selectedTheme))
        }
    }

    private fun updatePaletteSelection() {
        val light = model.value.currentLightPalette
        val dark = model.value.currentDarkPalette
        val palette = when (model.value.currentTheme) {
            AppTheme.LIGHT -> light
            AppTheme.SYSTEM -> if (model.value.isDarkTheme) dark else light
            else -> dark
        }

        _featureState.update { model ->
            val filledPalette = check(model.palettes.filled, palette.ordinal)
            val outlinedPalette = check(model.palettes.outlined, palette.ordinal)

            return@update model.copy(
                palettes = model.palettes.copy(filled = filledPalette, outlined = outlinedPalette),
            )
        }
    }

    private fun check(array: Array<ColorPalette>, paletteOrdinal: Int): Array<ColorPalette> =
        array.map { item ->
        val isSelected = item.palette.ordinal == paletteOrdinal

        return@map item.copy(isSelected = isSelected)
    }.toTypedArray()

    private fun setDefaultPaletteWhenHighContrastThemeEnabled(appTheme: AppTheme) {
        if (appTheme == AppTheme.HIGH_CONTRAST) {
            setThemePalette(AppThemePalette.BLUE)
        }
    }

    fun setTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            updateThemeSelection(appTheme.ordinal)
            themeSelector.setTheme(appTheme)
            setDefaultPaletteWhenHighContrastThemeEnabled(appTheme)
        }
    }

    fun setThemePalette(palette: AppThemePalette) {
        viewModelScope.launch {
            val isDark = when (model.value.currentTheme) {
                AppTheme.LIGHT -> false
                AppTheme.SYSTEM -> model.value.isDarkTheme
                AppTheme.DARK -> true
                AppTheme.HIGH_CONTRAST -> true
            }
            updatePaletteSelection()
            paletteSelector.setPalette(isDark, palette)
        }
    }
}