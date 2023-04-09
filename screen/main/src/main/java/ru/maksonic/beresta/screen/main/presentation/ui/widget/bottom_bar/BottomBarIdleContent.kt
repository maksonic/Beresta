package ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectionBottomPanelItem
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 21.02.2023
 */
@Composable
internal fun BottomBarIdleContent(
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        SelectionBottomPanelItem(
            icon = AppIcon.Settings,
            action = { send(Msg.Ui.OnBottomBarSettingsClicked) }),
        SelectionBottomPanelItem(
            icon = AppIcon.Trash,
            action = { send(Msg.Ui.OnBottomBarTrashClicked) }),
        SelectionBottomPanelItem(
            icon = AppIcon.FolderOpen,
            action = { send(Msg.Ui.OnBottomBarFoldersClicked) }),
        SelectionBottomPanelItem(
            icon = AppIcon.SortBy,
            action = { send(Msg.Ui.OnBottomBarSortNotesByClicked) }),
        SelectionBottomPanelItem(
            icon = AppIcon.GridView,
            action = { send(Msg.Ui.OnSwitchViewClicked) }),
    )

    Column {
        Row(
            modifier
                .padding(start = dp4)
                .height(Theme.widgetSize.bottomMainPanelHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions.forEach { panelItem ->
                IconAction(
                    icon = { panelItem.icon },
                    action = panelItem.action,
                    tint = onSurface
                )
            }
        }
        Spacer(Modifier.height(SystemNavigationBarHeight))
    }
}