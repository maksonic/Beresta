package ru.maksonic.beresta.feature.folders_list.core.screen.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Msg
import ru.maksonic.beresta.feature.folders_list.core.screen.ui.SendMessage
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectionBottomPanelItem
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.bar.BottomRippleBar

/**
 * @Author maksonic on 05.04.2023
 */
@Composable
internal fun BottomBarContent(
    send: SendMessage,
    panelCounterApi: SelectedItemsPanelUiApi,
    selectedCount: () -> Int,
    isShowUnpin: Boolean,
    backgroundColor: () -> Color,
    modifier: Modifier = Modifier
) {

    val editFolderItem = if (selectedCount() > 1) null else SelectionBottomPanelItem(
        label = text.shared.btnTitleChange,
        icon = AppIcon.Edit,
        action = {
            send(Msg.Ui.OnEditFolderClicked)
            send(Msg.Ui.OnCancelSelectionClicked)
        }
    )
    
    val panelItems = arrayOf(
        SelectionBottomPanelItem(
            label = if (isShowUnpin) text.shared.btnTitleUnpin else text.shared.btnTitlePin,
            icon = if (isShowUnpin) AppIcon.Unpin else AppIcon.Pin,
            action = {
                send(Msg.Ui.OnPinSelectedFoldersClicked)
                send(Msg.Ui.OnCancelSelectionClicked)
            }
        ),
        SelectionBottomPanelItem(
            label = text.shared.btnTitleRemove,
            icon = AppIcon.MoveTrash,
            action = { send(Msg.Ui.OnRemoveSelectedFoldersClicked) }
        ),
        editFolderItem
    )

    Column(modifier.drawBehind { drawRect(backgroundColor()) }) {
        panelCounterApi.Widget(
            onSelectAction = { send(Msg.Ui.OnSelectAllFoldersClicked) },
            onCancelAction = { send(Msg.Ui.OnCancelSelectionClicked) },
            countValue = selectedCount
        )

        BottomAppBar(
            Modifier.fillMaxWidth(),
            backgroundColor = transparent,
            elevation = Theme.elevation.Level0,
        ) {
            panelItems.forEach { item ->
                if (item != null) {
                    BottomRippleBar(
                        selected = true,
                        onClick = item.action,
                        label = { Text(item.label) },
                        icon = { Icon(item.icon, contentDescription = "") },
                        unselectedContentColor = outline,
                        selectedContentColor = onBackground,
                    )
                }
            }
        }
        SystemNavigationBar(backgroundColor = { backgroundColor() })
    }
}