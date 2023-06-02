package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem
import ru.maksonic.beresta.feature.notes.list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListSharedUiState
import ru.maksonic.beresta.screen.main.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.FolderOpen
import ru.maksonic.beresta.ui.theme.icons.GridView
import ru.maksonic.beresta.ui.theme.icons.Settings
import ru.maksonic.beresta.ui.theme.icons.SortBy
import ru.maksonic.beresta.ui.theme.icons.Trash
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.bar.BottomRippleBar
import ru.maksonic.beresta.ui.widget.bar.DisabledBottomBarPlaceholder
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 24.03.2023
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun MainBottomBar(
    state: MainBottomBarState,
    send: SendMessage,
    notesListSharedUiState: State<NotesListSharedUiState>,
    modifier: Modifier = Modifier
) {
    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val panelHeight = Theme.widgetSize.bottomMainBarHeight.plus(navBarHeight)
    val isSelectionState = state == MainBottomBarState.SELECTION
    val panelOffset = animateDpAsState(
        targetValue = if (isSelectionState) 0.dp
        else if (notesListSharedUiState.value.isVisibleBottomBar) 0.dp else panelHeight,
        animationSpec = tween(Theme.animSpeed.common), label = ""
    )

    Column(
        modifier
            .fillMaxWidth()
            .graphicsLayer {
                translationY = panelOffset.value.toPx()
            },
    ) {
        SurfacePro(
            tonalElevation = Theme.tonal.Level4,
            modifier = Modifier.fillMaxWidth()
        ) { color ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .drawBehind { drawRect(color) }
            ) {

                AnimatedContent(targetState = state, label = "") { barState ->
                    when (barState) {
                        MainBottomBarState.IDLE -> {
                            IdleStateContent(
                                send,
                                notesListSharedUiState.value.onChangeGridCount
                            )
                        }

                        MainBottomBarState.SELECTION -> {
                            Box {
                                SelectedStateContent(notesListSharedUiState.value.selectBarActions)
                                if (!notesListSharedUiState.value.isEnabledBottomBar)
                                    DisabledBottomBarPlaceholder()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IdleStateContent(
    send: SendMessage,
    onChangeGridCountClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val actions = arrayOf(
        BaseBottomBarItem(
            icon = AppIcon.Settings,
            action = { send(Msg.Ui.OnBottomBarSettingsClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.Trash,
            action = { send(Msg.Ui.OnBottomBarTrashClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.FolderOpen,
            action = { send(Msg.Ui.OnBottomBarFoldersClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.SortBy,
            action = { send(Msg.Ui.OnBottomBarSortNotesByClicked) }),
        BaseBottomBarItem(
            icon = AppIcon.GridView,
            action = onChangeGridCountClicked
        ),
    )

    Column {
        Row(
            modifier
                .padding(start = dp4)
                .height(Theme.widgetSize.bottomMainBarHeight),
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

@Composable
private fun SelectedStateContent(actions: Array<BaseBottomBarItem>, modifier: Modifier = Modifier) {
    Box(modifier.navigationBarsPadding()) {
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