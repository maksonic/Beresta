package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.PanelSharedState
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.R
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.button.IconPrimaryAction

/**
 * @Author maksonic on 25.12.2022
 */
@Composable
internal fun IdlePanelState(panelState: PanelSharedState, modifier: Modifier = Modifier) {

    val leftButtons = arrayOf(
        PanelItem(iconId = R.drawable.ic_trash, action = {
            panelState.idleActions[BottomPanel.Action.Notes.Idle.TRASH]?.invoke()
        }),
        PanelItem(iconId = R.drawable.ic_folder_open, action = {
            panelState.idleActions[BottomPanel.Action.Notes.Idle.FOLDERS]?.invoke()
        }),
        PanelItem(iconId = R.drawable.ic_search, action = {
            panelState.idleActions[BottomPanel.Action.Notes.Idle.SEARCH]?.invoke()
        }),
    )
    val rightButtons = arrayOf(
        PanelItem(iconId = R.drawable.ic_favorite_outlined, action = {
            panelState.idleActions[BottomPanel.Action.Notes.Idle.FAVORITES]?.invoke()
        }),
        PanelItem(iconId = R.drawable.ic_sorting, action = {
            panelState.idleActions[BottomPanel.Action.Notes.Idle.SORT_BY]?.invoke()
        }),
        PanelItem(iconId = R.drawable.ic_grid_view, action = {
            panelState.idleActions[BottomPanel.Action.Notes.Idle.SWITCH_VIEW_STATE]?.invoke()
        }),
    )

    Row(
        modifier
            .height(Theme.widgetSize.bottomPanelHeightIdle)
            .padding(start = dp4, end = dp4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftButtons.forEach { item ->

            IconAction(
                icon = painterResource(id = item.iconId),
                action = item.action,
                modifier = modifier.padding(start = dp4)
            )
        }

        Spacer(modifier.weight(1f))
        IconPrimaryAction(action = { })
        Spacer(modifier.weight(1f))

        rightButtons.forEach { item ->
            IconAction(
                icon = painterResource(id = item.iconId),
                action = item.action,
                modifier = modifier.padding(end = dp4)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IdlePanelStatePreview() {
    BerestaTheme {
        IdlePanelState(panelState = PanelSharedState())
    }
}