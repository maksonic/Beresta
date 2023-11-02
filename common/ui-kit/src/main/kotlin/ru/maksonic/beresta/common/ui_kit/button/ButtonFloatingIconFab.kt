package ru.maksonic.beresta.common.ui_kit.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Handshake
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
fun ButtonFloatingIconFab(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    fabColor: Color = tertiaryContainer,
    iconColor: Color = onTertiaryContainer,
    rippleColor: Color = onTertiaryContainer,
    shape: Shape = Theme.shape.cornerLarge,
    shadowElevation: Dp = Theme.elevation.level3,
    border: BorderStroke? = null,
    contentDescription: String = "",
) {
    SurfacePro(
        color = fabColor,
        shape = shape,
        shadowElevation = shadowElevation,
        border = border,
        modifier = modifier
            .semantics { role = Role.Button }) {
        Box(
            Modifier
                .defaultMinSize(Theme.size.btnFabSize, Theme.size.btnFabSize)
                .rippledClick(enabled = enabled, rippleColor = rippleColor, onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            IconBase(icon, tint = iconColor, contentDescription = contentDescription)
        }
    }
}

@Preview
@Composable
private fun ButtonFloatingFabPreview() {
    PreviewContainer {
        ButtonFloatingIconFab(AppIcon.Handshake)
    }
}