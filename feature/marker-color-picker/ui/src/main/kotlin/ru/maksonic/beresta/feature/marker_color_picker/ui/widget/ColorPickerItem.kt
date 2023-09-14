package ru.maksonic.beresta.feature.marker_color_picker.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.widget.CircleColorPickerItem

/**
 * @Author maksonic on 11.09.2023
 */

@Composable
internal fun ColorPickerItem(
    isSelected: Boolean,
    color: Color,
    onColorClicked: () -> Unit,
    modifier: Modifier
) {
    CircleColorPickerItem(
        isSelected = isSelected,
        onClick = onColorClicked,
        backgroundColor = color,
        checkMarkColor = secondaryContainer,
        modifier = modifier
    )
}