package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.botom_panel.api.PanelSharedState
import ru.maksonic.beresta.feature.bottom_panel.ui.R
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.R.drawable
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 19.01.2023
 */

@Composable
fun SelectPanelState(
    modifier: Modifier = Modifier,
    feature: BottomPanelFeature = get(),
    panelState: PanelSharedState,
) {

    Column {
        feature.PanelWithSelectCounter(
            onSelectAction = {
                panelState.selectActions[BottomPanel.Action.Notes.Select.SELECT_ALL]?.invoke()
            },
            onCancelAction = {
                panelState.selectActions[BottomPanel.Action.Notes.Select.CANCEL]?.invoke()
            },
            countValue = { panelState.selectedCount },
            modifier = modifier
        )

        BottomNavigation(backgroundColor = tertiaryContainer, elevation = Theme.elevation.disable) {

            arrayBottomItems(panelState).forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(item.iconId), contentDescription = null) },
                    label = { Text(stringResource(id = requireNotNull(item.titleId))) },
                    selected = false,
                    onClick = { item.action() },
                    unselectedContentColor = onTertiary,
                    selectedContentColor = primary,
                )
            }
        }
    }
}

/*@Composable
private fun AbovePanelWithCounter(panelState: PanelSharedState, modifier: Modifier = Modifier) {
    Row(
        modifier
            .height(Theme.widgetSize.bottomPanelHeightDefault)
            .padding(start = dp8, end = dp8)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAction(icon = painterResource(id = drawable.ic_select_all), action = {
            panelState.selectActions[BottomPanel.Action.Notes.Select.SELECT_ALL]?.invoke()
        })
        SelectedNotesCount(countNotes = { panelState.selectedCount })
        IconAction(icon = painterResource(id = drawable.ic_close), action = {
            panelState.selectActions[BottomPanel.Action.Notes.Select.CANCEL]?.invoke()
        })
    }
}*/

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SelectedNotesCount(countNotes: () -> Int, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.txt_helper_select_notes_count),
            style = TextDesign.body.copy(fontWeight = FontWeight.Bold),
            modifier = modifier.padding(start = dp8, top = dp4, bottom = dp4)
        )

        AnimatedContent(
            targetState = countNotes(),
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                text = "$targetCount",
                style = TextDesign.body.copy(fontWeight = FontWeight.Bold),
                modifier = modifier.padding(end = dp8)
            )
        }
    }
}

@Composable
private fun arrayBottomItems(panelState: PanelSharedState): Array<PanelItem> {
    return arrayOf(
        PanelItem(
            iconId = drawable.ic_lock,
            titleId = R.string.item_selected_lock,
            action = { panelState.selectActions[BottomPanel.Action.Notes.Select.HIDE]?.invoke() }
        ),
        PanelItem(
            iconId = if (panelState.isShowUnpinButton) drawable.ic_unpin else drawable.ic_pin,
            titleId = if (panelState.isShowUnpinButton)
                R.string.item_selected_unpin
            else
                R.string.item_selected_pin,
            action = { panelState.selectActions[BottomPanel.Action.Notes.Select.PIN]?.invoke() }
        ),
        PanelItem(
            iconId = drawable.ic_move,
            titleId = R.string.item_selected_replace,
            action = { panelState.selectActions[BottomPanel.Action.Notes.Select.REPLACE]?.invoke() }
        ),
        PanelItem(
            iconId = drawable.ic_remove,
            titleId = R.string.item_selected_remove,
            action = { panelState.selectActions[BottomPanel.Action.Notes.Select.REMOVE]?.invoke() }
        ),
    )
}


@Preview(showBackground = true)
@Composable
private fun SelectPanelStatePreview() {
    BerestaTheme {
        SelectPanelState(panelState = PanelSharedState())
    }
}