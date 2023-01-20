package ru.maksonic.beresta.feature.botom_panel.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.update
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanel
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelSharedState
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
fun SelectPanelState(state: BottomPanelSharedState) {
    val items = arrayOf(
        PanelItem(
            iconId = drawable.ic_lock,
            title = stringResource(id = R.string.item_selected_lock),
            action = {}
        ),
        PanelItem(
            iconId = drawable.ic_pin,
            title = stringResource(id = R.string.item_selected_pin),
            action = {}
        ),
        PanelItem(
            iconId = drawable.ic_move,
            title = stringResource(id = R.string.item_selected_replace),
            action = {}
        ),
        PanelItem(
            iconId = drawable.ic_remove,
            title = stringResource(id = R.string.item_selected_remove),
            action = {}
        ),
    )
    Column {

        AbovePanelWithCounter(state)

        BottomNavigation(backgroundColor = tertiaryContainer, elevation = Theme.elevation.disable) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(item.iconId), contentDescription = null) },
                    label = { Text(item.title) },
                    selected = false,
                    onClick = { item.action },
                    unselectedContentColor = onTertiary,
                    selectedContentColor = primary,
                )
            }
        }
    }
}

@Composable
private fun AbovePanelWithCounter(state: BottomPanelSharedState, modifier: Modifier = Modifier) {
    val count = state.state.collectAsState().value.selectedCount

    Row(
        modifier
            .height(Theme.widgetSize.bottomPanelHeightDefault).padding(start = dp8, end = dp8)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAction(icon = painterResource(id = drawable.ic_select_all), action = {
            state.mutableState.update { it.copy(action = BottomPanel.Action.SELECT_ALL) }
        })
        SelectedNotesCount(countNotes = { count })
        IconAction(icon = painterResource(id = drawable.ic_close), action = {
            state.mutableState.update { it.copy(action = BottomPanel.Action.CANCEL) }
        })
    }
}

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


@Preview(showBackground = true)
@Composable
private fun SelectPanelStatePreview() {
    BerestaTheme {
        SelectPanelState(state = BottomPanelSharedState())
    }
}