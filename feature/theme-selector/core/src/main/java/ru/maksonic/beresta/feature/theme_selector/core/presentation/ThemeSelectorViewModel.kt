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
import ru.maksonic.beresta.feature.theme_selector.api.ThemeUi
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ThemeContrast
import ru.maksonic.beresta.ui.theme.icons.ThemeLight
import ru.maksonic.beresta.ui.theme.icons.ThemeNight
import ru.maksonic.beresta.ui.theme.icons.ThemeSystem

/**
 * @Author maksonic on 20.02.2023
 */
data class Model(
    val themes: ThemesCollection,
    val palettes: Array<ColorPalette>
)

class ThemeSelectorViewModel(private val selector: ThemeSelectorApi.Theme) : ViewModel() {

    private val uiData = arrayOf(
        ThemeUi(id = AppTheme.SYSTEM.ordinal, AppTheme.SYSTEM, icon = AppIcon.ThemeSystem),
        ThemeUi(id = AppTheme.LIGHT.ordinal, AppTheme.LIGHT, icon = AppIcon.ThemeLight),
        ThemeUi(id = AppTheme.DARK.ordinal, AppTheme.DARK, icon = AppIcon.ThemeNight),
        ThemeUi(
            id = AppTheme.HIGH_CONTRAST.ordinal,
            AppTheme.HIGH_CONTRAST,
            icon = AppIcon.ThemeContrast
        )
    )

    private val colorPaletteListData = arrayOf(
        ColorPalette(ThemeColorPalette.BLUE),
        ColorPalette(ThemeColorPalette.GREEN),
        ColorPalette(ThemeColorPalette.PURPLE),
        ColorPalette(ThemeColorPalette.RED),
        ColorPalette(ThemeColorPalette.ORANGE),
        ColorPalette(ThemeColorPalette.YELLOW)
    )

    private val _model = MutableStateFlow(
        Model(
            themes = ThemesCollection(uiData),
            palettes = colorPaletteListData
        )
    )
    val model = _model.asStateFlow()

    init {
        viewModelScope.launch {
            combine(selector.currentTheme, selector.currentPalette) { theme, palette ->
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
            val selectedPalette = model.palettes.map { item ->
                val isSelected = item.palette.ordinal == paletteOrdinal
                item.copy(isSelected = isSelected)
            }.toTypedArray()
            model.copy(palettes = selectedPalette)
        }
    }

    fun setTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            updateThemeSelectionState(appTheme.ordinal)
            selector.setTheme(appTheme)
        }
    }

    fun setThemePalette(palette: ThemeColorPalette) {
        viewModelScope.launch {
            updatePaletteSelectionState(palette.ordinal)
            selector.setColorPalette(palette)
        }
    }
}