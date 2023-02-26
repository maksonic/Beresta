package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 25.02.2023
 */
@Composable
internal fun ThemePaletteColorPickerWidget(
    filledPalettes: Array<ColorPalette>,
    outlinedPalettes: Array<ColorPalette>,
    currentTheme: () -> AppTheme,
    onChangePalette: (ThemeColorPalette) -> Unit,
) {
    Column {
        AnimateFadeInOut(currentTheme() != AppTheme.HIGH_CONTRAST) {
            ThemePaletteOutlinedColorPicker(outlinedPalettes, onChangePalette)
        }
        ThemePaletteFilledColorPicker(filledPalettes, onChangePalette)
    }
}