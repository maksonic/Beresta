package ru.maksonic.beresta.feature.theme_picker.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.feature.theme_picker.api.ThemeUiModel
import ru.maksonic.beresta.feature.theme_picker.core.ui.FeatureState
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette

/**
 * @Author maksonic on 24.04.2023
 */
class ThemePickerViewModel(
    private val themeApi: ThemePickerApi.Theme,
    private val paletteApi: ThemePickerApi.Palette,
    private val darkModeChecker: ThemePickerApi.DarkModeChecker,
) : ViewModel() {

    private val _featureState = MutableStateFlow(FeatureState.Initial)
    val model = _featureState.asStateFlow()

    init {
        initFeatureState()
    }

    private fun initFeatureState() {
        viewModelScope.launch {
            val isEnabledDarkMode = darkModeChecker.isEnabledSystemDarkModeState
            val themeState = themeApi.current
            val paletteState = paletteApi.current

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

    private fun check(
        array: Array<ThemeUiModel.Palette>,
        paletteOrdinal: Int
    ): Array<ThemeUiModel.Palette> = array.map { item ->
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
            themeApi.setTheme(appTheme)
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
            this@ThemePickerViewModel.paletteApi.setPalette(isDark, palette)
        }
    }
}