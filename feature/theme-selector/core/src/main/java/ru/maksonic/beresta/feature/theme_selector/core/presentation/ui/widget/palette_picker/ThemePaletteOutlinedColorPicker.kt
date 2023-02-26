package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
import ru.maksonic.beresta.ui.theme.component.dp2
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 26.02.2023
 */
@Composable
internal fun ThemePaletteOutlinedColorPicker(
    palettes: Array<ColorPalette>,
    onChangePalette: (ThemeColorPalette) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize)
            .padding(dp6),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val colors = arrayOf(
            Theme.color.black,
            Theme.color.blue,
            Theme.color.green,
            Theme.color.purple,
            Theme.color.red,
            Theme.color.orange,
            Theme.color.yellow,
        )
        palettes.forEach { item ->
            ColorPickerOutlinedItem(
                item = item,
                selectColor = { onChangePalette(item.palette) },
                pickColor = { colors[item.id] },
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ColorPickerOutlinedItem(
    item: ColorPalette,
    selectColor: () -> Unit,
    pickColor: () -> Color,
    modifier: Modifier
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        BoxWithScaleInOutOnClick(onClick = { selectColor() }) {
            BoxWithConstraints(
                modifier
                    .fillMaxHeight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(dp2, pickColor(), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CheckWidget(
                    isSelected = { item.isSelected },
                    scope = this,
                    checkColor = { pickColor() })
            }
        }
    }
}