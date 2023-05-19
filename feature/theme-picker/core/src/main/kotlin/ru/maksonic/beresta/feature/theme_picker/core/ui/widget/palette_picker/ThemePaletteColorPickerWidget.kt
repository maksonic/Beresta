package ru.maksonic.beresta.feature.theme_picker.core.ui.widget.palette_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.theme_picker.core.ui.FeatureState
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 25.02.2023
 */
@Composable
internal fun ThemePaletteColorPickerWidget(
    uiState: FeatureState,
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
        AnimateFadeInOut(uiState.currentTheme != AppTheme.HIGH_CONTRAST) {
            PickerRowContainer(
                isFilledPalette = false,
                palettes = uiState.palettes.outlined,
                colors = outlinedColors,
                onChangePalette = onChangePalette
            )
        }
        PickerRowContainer(
            isFilledPalette = true,
            palettes = uiState.palettes.filled,
            colors = filledColors,
            onChangePalette = onChangePalette
        )
    }
}

@Preview
@Composable
fun Preview() {
    BerestaTheme {
        ThemePaletteColorPickerWidget(uiState = FeatureState.Initial, onChangePalette = {})
    }
}