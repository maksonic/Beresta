package ru.maksonic.beresta.common.ui_kit.button

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.surface

/**
 * @Author maksonic on 04.11.2023
 */
@Composable
fun ButtonCircle(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color = surface,
    iconTint: Color = onSurface,
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
            Icon(imageVector = icon, tint = iconTint, contentDescription = "")
        }
    }
}