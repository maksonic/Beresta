package ru.maksonic.beresta.common.ui_kit.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.common.ui_theme.colors.onBackground

/**
 * @Author maksonic on 15.10.2023
 */
@Composable
fun IconBase(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = onBackground,
    contentDescription: String = ""
) {
    Icon(
        imageVector = icon,
        tint = tint,
        contentDescription = contentDescription,
        modifier = modifier
    )
}