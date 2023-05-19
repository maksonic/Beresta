package ru.maksonic.beresta.feature.theme_picker.core.ui.widget.palette_picker

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done

/**
 * @Author maksonic on 28.02.2023
 */
@Composable
internal fun SelectedItemWidget(
    isSelected: () -> Boolean,
    checkColor: () -> Color,
    scope: BoxWithConstraintsScope,
    modifier: Modifier = Modifier
) {
    val labelSize = animateDpAsState(if (isSelected()) scope.maxHeight else 0.dp, label = "")

    Icon(
        imageVector = AppIcon.Done,
        tint = checkColor(),
        contentDescription = "",
        modifier = modifier.size(labelSize.value)
    )
}