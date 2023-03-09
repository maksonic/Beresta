package ru.maksonic.beresta.ui.widget.button

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.primary

/**
 * @Author maksonic on 21.11.2022
 */
@Composable
fun IconAction(
    modifier: Modifier = Modifier,
    icon: @Composable () -> ImageVector,
    tint: Color = onBackground,
    ripple: Color = primary,
    contentDescription: String = "",
    action: () -> Unit
) {
    BaseClickableIcon(onClick = action, rippleColor = ripple, modifier = modifier) {
        Icon(imageVector = icon(), contentDescription = contentDescription, tint = tint)
    }
}