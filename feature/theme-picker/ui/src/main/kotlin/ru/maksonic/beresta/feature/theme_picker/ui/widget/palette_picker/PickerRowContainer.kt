package ru.maksonic.beresta.feature.theme_picker.ui.widget.palette_picker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.theme_picker.api.ThemeUiModel
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.dp2
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

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
            CircleColorPickerItem(
                isCurrent = item.palette == currentPalette,
                isFilledPalette = isFilledPalette,
                selectColor = { onChangePalette(item.palette) },
                pickColor = rememberUpdatedState(colors[item.id]),
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
internal fun CircleColorPickerItem(
    isCurrent: Boolean,
    isFilledPalette: Boolean,
    selectColor: () -> Unit,
    pickColor: State<Color>,
    modifier: Modifier
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        BoxWithScaleInOutOnClick(onClick = selectColor) {
            val checkColor = rememberUpdatedState(if (isFilledPalette) surface else pickColor.value)
            val backgroundColor = if (isFilledPalette) pickColor.value else transparent
            BoxWithConstraints(
                modifier
                    .fillMaxHeight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(dp2, pickColor.value, CircleShape)
                    .drawBehind { drawRect(backgroundColor) },
                contentAlignment = Alignment.Center
            ) {
                CheckMark(
                    isCurrent = isCurrent,
                    scope = this,
                    checkColor = checkColor
                )
            }
        }
    }
}