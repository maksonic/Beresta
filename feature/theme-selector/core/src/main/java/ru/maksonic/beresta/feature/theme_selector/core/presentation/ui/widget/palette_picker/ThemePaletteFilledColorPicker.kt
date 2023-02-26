package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 26.02.2023
 */
@Composable
internal fun ThemePaletteFilledColorPicker(
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
            Theme.color.blue,
            Theme.color.green,
            Theme.color.purple,
            Theme.color.red,
            Theme.color.orange,
            Theme.color.yellow,
        )
        palettes.forEach { item ->
            ColorPickerFilledItem(
                item = item,
                selectColor = { onChangePalette(item.palette) },
                pickColor = { colors[item.id] },
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ColorPickerFilledItem(
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
            val checkColor = surface
            BoxWithConstraints(
                modifier
                    .fillMaxHeight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .drawBehind { drawRect(pickColor()) },
                contentAlignment = Alignment.Center
            ) {
                CheckWidget(
                    isSelected = { item.isSelected },
                    scope = this,
                    checkColor = { checkColor })
            }
        }
    }
}

@Composable
internal fun CheckWidget(
    isSelected: () -> Boolean,
    checkColor: () -> Color,
    scope: BoxWithConstraintsScope,
    modifier: Modifier = Modifier
) {
    val labelSize = animateDpAsState(if (isSelected()) scope.maxHeight else 0.dp)

    Icon(
        imageVector = AppIcon.Done,
        tint = checkColor(),
        contentDescription = "",
        modifier = modifier.size(labelSize.value)
    )
}
