package ru.maksonic.beresta.feature.theme_selector.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.feature.theme_selector.core.data.Palettes
import ru.maksonic.beresta.feature.theme_selector.core.data.ThemeRepository
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette

/**
 * @Author maksonic on 20.02.2023
 */
data class Model(
    val currentTheme: AppTheme = AppTheme.SYSTEM,
    val themes: ThemesCollection,
    val currentPalette: ThemeColorPalette = ThemeColorPalette.BLUE,
    val palettes: Palettes
)

class ThemeSelectorViewModel(
    private val selector: ThemeSelectorApi.Theme,
    repository: ThemeRepository
) : ViewModel() {

    private val _model = MutableStateFlow(
        Model(themes = repository.themes, palettes = repository.palettes)
    )
    val model = _model.asStateFlow()

    init {
        viewModelScope.launch {
            combine(selector.currentTheme, selector.currentPalette) { theme, palette ->
                _model.update { model -> model.copy(currentTheme = theme) }
                updateThemeSelectionState(theme.ordinal)
                updatePaletteSelectionState(palette.ordinal)
            }.collect()
        }
    }

    private fun updateThemeSelectionState(themeOrdinal: Int) {
        _model.update { model ->
            val selectedTheme = model.themes.data.map { item ->
                val isSelected = item.theme.ordinal == themeOrdinal
                item.copy(isSelected = isSelected)
            }.toTypedArray()
            model.copy(themes = model.themes.copy(data = selectedTheme))
        }
    }

    private fun updatePaletteSelectionState(paletteOrdinal: Int) {
        _model.update { model ->
            val selectedFilledPalette = compareSelection(model.palettes.filled, paletteOrdinal)
            val selectedOutlinedPalette = compareSelection(model.palettes.outlined, paletteOrdinal)
            model.copy(
                palettes = model.palettes.copy(
                    filled = selectedFilledPalette,
                    outlined = selectedOutlinedPalette
                )
            )
        }
    }

    private fun compareSelection(
        array: Array<ColorPalette>,
        paletteOrdinal: Int
    ): Array<ColorPalette> = array.map { item ->
        val isSelected = item.palette.ordinal == paletteOrdinal
        item.copy(isSelected = isSelected)
    }.toTypedArray()

    fun setTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            updateThemeSelectionState(appTheme.ordinal)
            selector.setTheme(appTheme)
            if (appTheme == AppTheme.HIGH_CONTRAST) {
                setThemePalette(ThemeColorPalette.BLUE)
            }
        }
    }

    fun setThemePalette(palette: ThemeColorPalette) {
        viewModelScope.launch {
            updatePaletteSelectionState(palette.ordinal)
            selector.setColorPalette(palette)
        }
    }
}