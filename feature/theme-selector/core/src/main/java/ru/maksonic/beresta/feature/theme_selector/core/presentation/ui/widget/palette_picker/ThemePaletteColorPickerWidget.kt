package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 25.02.2023
 */
@Composable
internal fun ThemePaletteColorPickerWidget(
    filledPalettes: Array<ColorPalette>,
    outlinedPalettes: Array<ColorPalette>,
    currentTheme: () -> AppTheme,
    onChangePalette: (AppThemePalette) -> Unit,
) {
    val outlinedColors = arrayOf(
        Theme.color.black,
        Theme.color.blue,
        Theme.color.green,
        Theme.color.purple,
        Theme.color.red,
        Theme.color.orange,
        Theme.color.yellow,
    )
    //Remove outlined black color for filled palette
    val filledColors = outlinedColors.drop(1).toTypedArray()

    Column {
        AnimateFadeInOut(currentTheme() != AppTheme.HIGH_CONTRAST) {
            PickerRowContainer(
                isFilledPalette = false,
                palettes = outlinedPalettes,
                colors = outlinedColors,
                onChangePalette = onChangePalette
            )
        }
        PickerRowContainer(
            isFilledPalette = true,
            palettes = filledPalettes,
            colors = filledColors,
            onChangePalette = onChangePalette
        )
    }
}