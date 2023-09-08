package ru.maksonic.beresta.feature.theme_picker.ui.widget.palette_picker

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.theme_picker.ui.core.Model
import ru.maksonic.beresta.feature.theme_picker.ui.core.ThemeStore
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette

/**
 * @Author maksonic on 25.02.2023
 */
private const val BLACK_ITEM_COLOR_POSITION = 1

@Composable
internal fun PaletteColorPicker(
    model: State<Model>,
    onChangePalette: (AppThemePalette) -> Unit,
) {
    val outlineColors = arrayOf(
        Theme.color.black,
        Theme.color.blue,
        Theme.color.green,
        Theme.color.purple,
        Theme.color.red,
        Theme.color.orange,
        Theme.color.yellow,
    )

    Column {
        //Remove outline black color when filled high contrast palette is current
        val highContrastColors = outlineColors.drop(BLACK_ITEM_COLOR_POSITION).toTypedArray()

        if (model.value.currentTheme.first != AppTheme.HIGH_CONTRAST) {
            PickerRowContainer(
                currentPalette = model.value.currentPalette,
                isFilledPalette = false,
                palettes = ThemeStore.palettes.outlined,
                colors = outlineColors,
                onChangePalette = onChangePalette
            )
        }
        PickerRowContainer(
            currentPalette = model.value.currentPalette,
            isFilledPalette = true,
            palettes = ThemeStore.palettes.filled,
            colors = highContrastColors,
            onChangePalette = onChangePalette
        )
    }
}

@Preview
@Composable
private fun Preview() {
    BerestaTheme {
        val model = remember { mutableStateOf(Model.Initial) }
        PaletteColorPicker(model = model, onChangePalette = {})
    }
}