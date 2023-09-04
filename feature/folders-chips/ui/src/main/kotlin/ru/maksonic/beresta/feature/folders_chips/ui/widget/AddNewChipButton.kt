package ru.maksonic.beresta.feature.folders_chips.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.widget.functional.rippledClick

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun AddNewChipButton(
    color: State<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier
            .size(Theme.widgetSize.minimumTouchTargetSize)
            .clip(CircleShape)
            .drawBehind { drawRect(color.value) }, contentAlignment = Alignment.Center
    ) {
        Box(
            modifier
                .size(Theme.widgetSize.filterChipHeight)
                .clip(CircleShape)
                .drawBehind { drawRect(color.value) }
                .border(1.dp, onSurface, CircleShape)
                .rippledClick(rippleColor = primary, onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = AppIcon.Add, tint = onSurface, contentDescription = "")
        }
    }
}
