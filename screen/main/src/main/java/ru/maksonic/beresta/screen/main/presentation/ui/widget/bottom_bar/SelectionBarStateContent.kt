package ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.screen.main.presentation.MainBottomPanelItem
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.bar.BottomRippleBar
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 24.03.2023
 */
@Composable
internal fun SelectionBarStateContent(
    send: SendMessage,
    isShowUnpin: Boolean,
    selectedCount: () -> Int,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        MainBottomPanelItem(
            label = text.shared.btnTitleHide,
            icon = AppIcon.Lock,
            action = { send(Msg.Ui.OnHideSelectedNotesBottomBarClicked) }
        ),
        MainBottomPanelItem(
            label = if (isShowUnpin) text.shared.btnTitleUnpin else text.shared.btnTitlePin,
            icon = if (isShowUnpin) AppIcon.Unpin else AppIcon.Pin,
            action = {
                send(Msg.Ui.OnPinSelectedNotesBottomBarClicked)
                send(Msg.Ui.OnCancelSelectionClicked)
            }
        ),
        MainBottomPanelItem(
            label = text.shared.btnTitleReplace,
            icon = AppIcon.MoveFolder,
            action = { send(Msg.Ui.OnReplaceFolderSelectedNotesBottomBarClicked) }
        ),

        MainBottomPanelItem(
            label = text.shared.btnTitleRemove,
            icon = AppIcon.MoveTrash,
            action = { send(Msg.Ui.OnRemoveSelectedNotesBottomBarClicked) }
        ),
    )

    Column(
        modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        PanelWithSelectCounter(
            onSelectAction = { send(Msg.Ui.OnSelectAllNotesClicked) },
            onCancelAction = { send(Msg.Ui.OnCancelSelectionClicked) },
            countValue = selectedCount,
        )
        BottomAppBar(
            modifier.fillMaxWidth(),
            backgroundColor = transparent,
            elevation = 0.dp,
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

@Composable
private fun PanelWithSelectCounter(
    onSelectAction: () -> Unit,
    onCancelAction: () -> Unit,
    countValue: () -> Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .height(Theme.widgetSize.bottomPanelHeightDefault)
            .padding(start = dp8, end = dp8)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAction(
            icon = { AppIcon.Close },
            action = { onCancelAction() }
        )

        SelectedNotesCount(countNotes = { countValue() })

        IconAction(
            icon = { AppIcon.SelectAll },
            action = { onSelectAction() }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SelectedNotesCount(countNotes: () -> Int, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text.shared.hintSelectedNotesCount,
            style = TextDesign.bodyPrimary.copy(color = onPrimary, fontWeight = FontWeight.Bold),
            modifier = modifier.padding(start = dp8, top = dp4, bottom = dp4)
        )

        AnimatedContent(
            targetState = countNotes(),
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                text = "$targetCount",
                style = TextDesign.bodyPrimary.copy(
                    color = onPrimary,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier.padding(end = dp8)
            )
        }
    }
}