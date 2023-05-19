package ru.maksonic.beresta.feature.edit_note.core.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
 * @Author maksonic on 27.04.2023
 */
@Composable
fun EditNoteTopBar(backAction: () -> Unit, menuAction: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .padding(start = dp4, end = dp4),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconActionCircleOverflow(icon = AppIcon.ArrowBack, onBtnClicked = backAction)
        IconActionCircleOverflow(icon = AppIcon.MoreVertical, onBtnClicked = menuAction)
    }
}