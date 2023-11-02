package ru.maksonic.beresta.feature.ui.theme_picker.core

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Done
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp2
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.BerestaTheme
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.Model
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemeUi
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemeUiStore
import ru.maksonic.beresta.common.ui_kit.button.base.ScalableClickBox
import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi

/**
 * @Author maksonic on 25.02.2023
 */
private const val BLACK_ITEM_COLOR_POSITION = 1

@Composable
internal fun PaletteColorPicker(
    model: Model,
    onChangePalette: (AppThemePaletteUi) -> Unit,
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

    Column(Modifier.padding(start = dp16, end = dp16)) {
        //Remove outline black color when filled high contrast palette is current
        val highContrastColors = outlineColors.drop(BLACK_ITEM_COLOR_POSITION).toTypedArray()

        if (model.currentTheme != AppThemeUi.DARK) {
            PickerRowContainer(
                isFilledPalette = false,
                palettes = ThemeUiStore.palettes.outlined,
                colors = outlineColors,
                currentPaletteId = model.currentPalette.ordinal,
                onChangePalette = onChangePalette
            )
        }
        PickerRowContainer(
            isFilledPalette = true,
            palettes = ThemeUiStore.palettes.filled,
            colors = highContrastColors,
            currentPaletteId = model.currentPalette.ordinal,
            onChangePalette = onChangePalette
        )
    }
}

@Composable
private fun PickerRowContainer(
    isFilledPalette: Boolean,
    palettes: List<ThemeUi.Palette>,
    colors: Array<Color>,
    currentPaletteId: Int,
    onChangePalette: (AppThemePaletteUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.size.minimumTouchTargetSize)
            .padding(dp6),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        palettes.forEach { item ->
            val borderColor = colors[item.id]
            val backgroundColor = if (isFilledPalette) borderColor else transparent
            val checkMarkColor = if (isFilledPalette) surface else borderColor

            Item(
                isSelected = item.palette.ordinal == currentPaletteId,
                onClick = { onChangePalette(item.palette) },
                borderColor = borderColor,
                backgroundColor = backgroundColor,
                checkMarkColor = checkMarkColor,
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun Item(
    isSelected: Boolean,
    onClick: () -> Unit,
    backgroundColor: Color,
    borderColor: Color = backgroundColor,
    checkMarkColor: Color,
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        ScalableClickBox(onClick = onClick) {
            BoxWithConstraints(
                modifier
                    .fillMaxHeight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(dp2, borderColor, CircleShape)
                    .drawBehind { drawRect(backgroundColor) },
                contentAlignment = Alignment.Center
            ) {
                AnimateFadeInOut(isSelected) {
                    Icon(
                        imageVector = AppIcon.Done,
                        contentDescription = "",
                        tint = checkMarkColor,
                        modifier = modifier
                            .size(this.maxHeight)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    BerestaTheme {
        PaletteColorPicker(model = Model.Initial, onChangePalette = {})
    }
}