package ru.maksonic.beresta.feature.theme_selector.core.presentation.ui.widget.palette_picker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.dp2
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 28.02.2023
 */
@Composable
internal fun CirclePickerItemWidget(
    item: ColorPalette,
    isFilledPalette: Boolean,
    selectColor: () -> Unit,
    pickColor: () -> Color,
    modifier: Modifier
) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        BoxWithScaleInOutOnClick(onClick = { selectColor() }) {
            val checkColor = if (isFilledPalette) surface else pickColor()
            val backgroundColor = if (isFilledPalette) pickColor() else transparent
            BoxWithConstraints(
                modifier
                    .fillMaxHeight(1f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(dp2, pickColor(), CircleShape)
                    .drawBehind { drawRect(backgroundColor) },
                contentAlignment = Alignment.Center
            ) {
                SelectedItemWidget(
                    isSelected = { item.isSelected },
                    scope = this,
                    checkColor = { checkColor })
            }
        }
    }
}