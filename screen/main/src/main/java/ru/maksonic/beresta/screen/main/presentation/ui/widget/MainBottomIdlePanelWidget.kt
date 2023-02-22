package ru.maksonic.beresta.screen.main.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.screen.main.presentation.MainBottomPanelItem
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.FolderOpen
import ru.maksonic.beresta.ui.theme.icons.Settings
import ru.maksonic.beresta.ui.theme.icons.SortBy
import ru.maksonic.beresta.ui.theme.icons.Trash
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 21.02.2023
 */
private val actions = arrayOf(
    MainBottomPanelItem(icon = AppIcon.Settings, msg = Msg.Ui.OnSettingsClicked),
    MainBottomPanelItem(icon = AppIcon.Trash, msg = Msg.Ui.OnTrashClicked),
    MainBottomPanelItem(icon = AppIcon.FolderOpen, msg = Msg.Ui.OnOpenFoldersClicked),
    MainBottomPanelItem(icon = AppIcon.SortBy, msg = Msg.Ui.OnSortNotesByClicked),
)

@Composable
fun MainBottomIdlePanelWidget(send: SendMessage, modifier: Modifier = Modifier) {
    Row(
        modifier
            .noRippleClickable {}
            .fillMaxWidth()
            .height(Theme.widgetSize.bottomMainPanelHeight)
            .background(tertiaryContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier.size(dp4))

        actions.forEach { panelItem ->
            IconAction(icon = { panelItem.icon }, action = { send(panelItem.msg) })
        }

        Spacer(modifier.weight(1f))
        FloatingActionButton(
            onClick = {},
            containerColor = primary,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = Theme.elevation.disable
            ),
            modifier = modifier.padding(end = dp16)
        ) {
            Icon(imageVector = AppIcon.Add, tint = onPrimary, contentDescription = "")
        }
    }
}