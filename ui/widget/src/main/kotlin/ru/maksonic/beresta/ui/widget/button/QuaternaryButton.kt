package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ExpandMore
import ru.maksonic.beresta.ui.widget.functional.noRippleClick

/**
 * @Author maksonic on 15.07.2023
 */
@Composable
fun QuaternaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    title: String,
    prefixIcon: ImageVector,
    onNavigateToFoldersClicked: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(top = dp16, bottom = dp16)
            .height(Theme.widgetSize.btnPrimaryHeight)
            .clip(Shape.cornerNormal)
            .background(surfaceVariant)
            .clickable(
                indication = rememberRipple(bounded = true, color = primary),
                interactionSource = remember { MutableInteractionSource() }
            ) { onNavigateToFoldersClicked() },
        contentAlignment = Alignment.Center
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = prefixIcon,
                tint = onBackground,
                contentDescription = "",
                modifier = Modifier
                    .padding(start = dp16)
            )

            Text(
                text = title,
                style = TextDesign.title.copy(onBackground),
                modifier = Modifier.padding(start = dp16)
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = AppIcon.ExpandMore,
                tint = onBackground,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = dp16)
                    .rotate(-90f)
            )
        }
        if (!isEnabled) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(surface.copy(alpha = 0.3f))
                    .noRippleClick {})
        }
    }
}
