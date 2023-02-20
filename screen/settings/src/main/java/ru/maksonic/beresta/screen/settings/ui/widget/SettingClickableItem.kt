package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.rippleClickable

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun SettingClickableItem(
    icon: ImageVector,
    title: String,
    hint: String = "",
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize)
            .rippleClickable(rippleColor = primary) { action() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            tint = onTertiary,
            contentDescription = "",
            modifier = modifier.padding(start = dp16)
        )
        Text(
            text = title,
            style = TextDesign.title.copy(color = onTertiary),
            modifier = modifier.padding(start = dp16)
        )
        Spacer(modifier.weight(1f))

        Text(
            text = hint,
            style = TextDesign.body.copy(color = secondary),
            modifier = modifier.padding(end = dp16)
        )
    }
}