package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.MoreVertical
import ru.maksonic.beresta.ui.widget.button.IconActionCircleOverflow

/**
 * @Author maksonic on 09.03.2023
 */
@Composable
internal fun EditNoteTopBarWidget(
    backPressed: () -> Unit,
    menuPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp4, end = dp4)
            .height(Theme.widgetSize.topBarNormalHeight),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconActionCircleOverflow(icon = AppIcon.ArrowBack, onBtnClicked = backPressed)
        IconActionCircleOverflow(icon = AppIcon.MoreVertical, onBtnClicked = menuPressed)
    }
}