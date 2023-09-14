package ru.maksonic.beresta.feature.theme_picker.ui.widget.palette_picker

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
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.CircleColorPickerItem

/**
 * @Author maksonic on 26.02.2023
 */
@Composable
internal fun PickerRowContainer(
    currentPalette: AppThemePalette,
    isFilledPalette: Boolean,
    palettes: List<ThemeUiModel.Palette>,
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
            val borderColor = colors[item.id]
            val backgroundColor = if (isFilledPalette) borderColor else transparent
            val checkMarkColor = if (isFilledPalette) surface else borderColor

            CircleColorPickerItem(
                isSelected = item.palette == currentPalette,
                onClick = { onChangePalette(item.palette) },
                borderColor = borderColor,
                backgroundColor = backgroundColor,
                checkMarkColor = checkMarkColor,
                modifier = modifier.weight(1f)
            )
        }
    }
}