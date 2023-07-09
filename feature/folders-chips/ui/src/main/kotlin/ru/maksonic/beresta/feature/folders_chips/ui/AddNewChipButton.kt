package ru.maksonic.beresta.feature.folders_chips.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.widget.button.ClickableIcon

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun AddNewChipButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(Theme.widgetSize.filterChipHeight)
            .clip(CircleShape)
            .border(1.dp, onSurface, CircleShape)
    ) {
        ClickableIcon(icon = AppIcon.Add, tint = onSurface, action = onClick)
    }
}
