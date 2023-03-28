package ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.screen.main.presentation.MainBottomPanelItem
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
fun IdleBarStateContent(
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        MainBottomPanelItem(
            icon = AppIcon.Settings,
            action = { send(Msg.Ui.OnBottomBarSettingsClicked) }),
        MainBottomPanelItem(
            icon = AppIcon.Trash,
            action = { send(Msg.Ui.OnBottomBarTrashClicked) }),
        MainBottomPanelItem(
            icon = AppIcon.FolderOpen,
            action = { send(Msg.Ui.OnBottomBarOpenFoldersClicked) }),
        MainBottomPanelItem(
            icon = AppIcon.SortBy,
            action = { send(Msg.Ui.OnBottomBarSortNotesByClicked) }),
        MainBottomPanelItem(
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