package ru.maksonic.beresta.feature.theme_picker.core.ui.widget.palette_picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.theme_picker.api.ThemeUiModel
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.component.dp6

/**
 * @Author maksonic on 26.02.2023
 */
@Composable
internal fun PickerRowContainer(
    isFilledPalette: Boolean,
    palettes: Array<ThemeUiModel.Palette>,
    colors: Array<Color>,
    onChangePalette: (AppThemePalette) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize)
            .padding(dp6),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        palettes.forEach { item ->
            CirclePickerItemWidget(
                item = item,
                isFilledPalette = isFilledPalette,
                selectColor = { onChangePalette(item.palette) },
                pickColor = { colors[item.id] },
                modifier = modifier.weight(1f)
            )
        }
    }
}