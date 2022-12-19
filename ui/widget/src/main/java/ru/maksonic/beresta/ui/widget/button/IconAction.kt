package ru.maksonic.beresta.ui.widget.button

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary

/**
 * @Author maksonic on 21.11.2022
 */
@Composable
fun IconAction(
    modifier: Modifier = Modifier,
    icon: Painter,
    rippleColor: Color = primary,
    action: () -> Unit
) {
    BaseClickableIcon(onClick = action, rippleColor = rippleColor, modifier = modifier) {
        Icon(painter = icon, contentDescription = "", tint = onTertiary)
    }
}