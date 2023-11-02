package ru.maksonic.beresta.feature.folders_list.ui.core.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icons.Add
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun AddNewChipButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier
            .size(Theme.size.minimumTouchTargetSize)
            .clip(CircleShape)
            .drawBehind { drawRect(backgroundColor) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier
                .size(Theme.size.chipHeight)
                .clip(CircleShape)
                .border(1.dp, onSurface, CircleShape)
                .rippledClick(primary, onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = AppIcon.Add, tint = onSurface, contentDescription = "")
        }
    }
}