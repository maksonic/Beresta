package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.Shape

/**
 * @Author maksonic on 19.06.2023
 */
@Composable
fun BaseClickableIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    rippleColor: Color = primary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .size(Theme.widgetSize.minimumTouchTargetSize)
            .clip(Shape.cornerRound)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(color = rippleColor, bounded = false, radius = 24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        val contentAlpha = if (enabled) LocalContentAlpha.current else ContentAlpha.disabled
        CompositionLocalProvider(LocalContentAlpha provides contentAlpha, content = content)
    }
}

@Composable
fun ClickableIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = onBackground,
    ripple: Color = primary,
    contentDescription: String = "",
    action: () -> Unit
) {
    BaseClickableIcon(onClick = action, rippleColor = ripple, modifier = modifier) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = tint)
    }
}

@Composable
fun ClickableCircleIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tint: Color = onBackground,
    colorState: State<Color> = remember { mutableStateOf(tint) },
    ripple: Color = primary,
    contentDescription: String = "",
    action: () -> Unit
) {

    BaseClickableIcon(
        onClick = action,
        rippleColor = ripple,
        modifier = modifier
            .clip(CircleShape)
            .drawBehind { drawRect(colorState.value) }) {
        Icon(imageVector = icon, contentDescription = contentDescription, tint = tint)
    }
}