package ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectionBottomPanelItem
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.bar.BottomRippleBar

/**
 * @Author maksonic on 24.03.2023
 */
@Composable
internal fun BottomBarSelectionContent(
    send: SendMessage,
    isShowUnpin: Boolean,
    selectedCount: () -> Int,
    panelCounterApi: SelectedItemsPanelUiApi,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        SelectionBottomPanelItem(
            label = text.shared.btnTitleHide,
            icon = AppIcon.Lock,
            action = { send(Msg.Ui.OnHideSelectedNotesBottomBarClicked) }
        ),
        SelectionBottomPanelItem(
            label = if (isShowUnpin) text.shared.btnTitleUnpin else text.shared.btnTitlePin,
            icon = if (isShowUnpin) AppIcon.Unpin else AppIcon.Pin,
            action = {
                send(Msg.Ui.OnPinSelectedNotesBottomBarClicked)
                send(Msg.Ui.OnCancelSelectionClicked)
            }
        ),
        SelectionBottomPanelItem(
            label = text.shared.btnTitleReplace,
            icon = AppIcon.MoveFolder,
            action = { send(Msg.Ui.OnReplaceNoteToFolderClicked) }
        ),

        SelectionBottomPanelItem(
            label = text.shared.btnTitleRemove,
            icon = AppIcon.MoveTrash,
            action = { send(Msg.Ui.OnRemoveSelectedNotesClicked) }
        ),
    )

    Column(
        modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {

        panelCounterApi.Widget(
            onSelectAction = { send(Msg.Ui.OnSelectAllNotesClicked) },
            onCancelAction = { send(Msg.Ui.OnCancelSelectionClicked) },
            countValue = selectedCount
        )

        BottomAppBar(
            modifier.fillMaxWidth(),
            backgroundColor = transparent,
            elevation = Theme.elevation.Level0,
        ) {
            actions.forEach { item ->
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
}