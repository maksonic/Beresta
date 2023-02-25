package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 25.02.2023
 */
@Composable
internal fun ThemePaletteColorPickerWidget(
    palettes: Array<ColorPalette>,
    onChangePalette: (ThemeColorPalette) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(bottom = dp8),
        horizontalArrangement = Arrangement.Center
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

            ColorPickerItem(
                selectColor = { onChangePalette(item.palette) },
                item = item,
                pickColor = { colors[item.palette.ordinal] }
            )
        }
    }
}

@Composable
private fun ColorPickerItem(
    item: ColorPalette,
    selectColor: () -> Unit,
    pickColor: () -> Color,
    modifier: Modifier = Modifier
) {

    BoxWithScaleInOutOnClick(onClick = { selectColor() }) {

        Box(
            modifier
                .padding(dp8)
                .size(36.dp)
                .clip(CircleShape)
                .drawBehind { drawRect(pickColor()) },
            contentAlignment = Alignment.Center
        ) {
            val labelSize = animateDpAsState(if (item.isSelected) 30.dp else 0.dp)

            Box(
                modifier
                    .size(labelSize.value)
                    .clip(CircleShape)
                    .border(2.dp, surface, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = AppIcon.Done, tint = surface, contentDescription = "")
            }
        }
    }
}
