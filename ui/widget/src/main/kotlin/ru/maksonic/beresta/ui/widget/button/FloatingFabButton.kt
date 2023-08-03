package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.functional.rippledClick
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 31.03.2023
 */
@Composable
fun FloatingFabButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    fabColor: Color = tertiaryContainer,
    rippleColor: Color = onTertiaryContainer,
    shape: Shape = ru.maksonic.beresta.ui.theme.component.Shape.cornerBig,
    shadowElevation: Dp = Theme.elevation.Level3,
    border: BorderStroke? = null,
    fabContent: @Composable () -> Unit
) {

    SurfacePro(color = fabColor,
        shape = shape,
        shadowElevation = shadowElevation,
        border = border,
        modifier = modifier
            .semantics { role = Role.Button }) {
        Box(
            Modifier
                .defaultMinSize(Theme.widgetSize.btnFabSize, Theme.widgetSize.btnFabSize)
                .rippledClick(enabled = enabled, rippleColor = rippleColor, onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            fabContent()
        }
    }
}