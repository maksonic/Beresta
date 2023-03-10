package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 02.03.2023
 */
@Composable
internal fun AddNewFilterButton(modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(Theme.widgetSize.filterChipHeight)
            .clip(CircleShape)
            .border(1.dp, primary, CircleShape)
    ) {
        IconAction(icon = { AppIcon.Add }, tint = primary) {

        }
    }
}
