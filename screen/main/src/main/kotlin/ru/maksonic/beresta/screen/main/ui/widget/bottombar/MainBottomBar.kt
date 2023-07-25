package ru.maksonic.beresta.screen.main.ui.widget.bottombar

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiScrollState
import ru.maksonic.beresta.feature.notes.api.ui.updateScroll
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.FolderOpen
import ru.maksonic.beresta.ui.theme.icons.Lock
import ru.maksonic.beresta.ui.theme.icons.MoveFolder
import ru.maksonic.beresta.ui.theme.icons.MoveTrash
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.Settings
import ru.maksonic.beresta.ui.theme.icons.SortBy
import ru.maksonic.beresta.ui.theme.icons.Trash
import ru.maksonic.beresta.ui.theme.icons.Unpin
import ru.maksonic.beresta.ui.widget.bar.bottom.BaseBottomBarItem
import ru.maksonic.beresta.ui.widget.bar.bottom.BottomBarOld
import ru.maksonic.beresta.ui.widget.bar.bottom.DisabledBottomBarPlaceholder
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 22.06.2023
 */
@Composable
internal fun MainBottomBar(
    state: State<MainBottomBarState>,
    send: SendMessage,
    isSelectionState: State<Boolean>,
    isVisibleBottomBar: State<Boolean>,
    isEnabledBar: State<Boolean>,
    isShowUnpinBtn: State<Boolean>,
    sharedNotesUiScrollState: SharedUiState<SharedNotesUiScrollState>,
    modifier: Modifier = Modifier
) {
    val offset = Theme.widgetSize.bottomMainBarHeight.plus(SystemNavigationBarHeight)
    val transition = animateDp(if (isVisibleBottomBar.value) 0.dp else offset)


    LaunchedEffect(isSelectionState.value) {
        sharedNotesUiScrollState.updateScroll(true)
    }

    Column(
        modifier
            .fillMaxWidth()
            .graphicsLayer { translationY = transition.value.toPx() }
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

                AnimatedContent(targetState = state.value, label = "") { barState ->
                    when (barState) {
                        MainBottomBarState.IDLE -> IdleBarContent(send)
                        MainBottomBarState.SELECTION -> {
                            SelectedBarContent(send, isEnabledBar, isShowUnpinBtn)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IdleBarContent(
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val items = arrayOf(
        BaseBottomBarItem(AppIcon.Settings, { send(Msg.Ui.OnBottomBarSettingsClicked) }),
        BaseBottomBarItem(AppIcon.Trash, { send(Msg.Ui.OnBottomBarTrashClicked) }),
        BaseBottomBarItem(AppIcon.FolderOpen, { send(Msg.Ui.OnBottomBarFoldersClicked) }),
        BaseBottomBarItem(AppIcon.SortBy, { send(Msg.Ui.OnBottomBarSortNotesClicked) }),
    )

    Column {
        Row(
            modifier
                .padding(start = dp4)
                .height(Theme.widgetSize.bottomMainBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { panelItem ->
                ClickableIcon(icon = panelItem.icon, action = panelItem.action, tint = onSurface)
            }
        }
        SystemNavigationBar()
    }
}

@Composable
private fun SelectedBarContent(
    send: SendMessage,
    isEnabledBar: State<Boolean>,
    isShowUnpinBtn: State<Boolean>
) {
    val items = arrayOf(
        BaseBottomBarItem(
            icon = AppIcon.Lock,
            action = { send(Msg.Ui.OnBottomBarHideSelectedNotesClicked) },
            label = text.shared.btnTitleHide,
        ),
        BaseBottomBarItem(
            label = if (isShowUnpinBtn.value) text.shared.btnTitleUnpin
            else text.shared.btnTitlePin,
            icon = if (isShowUnpinBtn.value) AppIcon.Unpin else AppIcon.Pin,
            action = {
                send(Msg.Ui.OnBottomBarPinSelectedNotesClicked)
                send(Msg.Ui.CancelNotesSelection)
            }),
        BaseBottomBarItem(
            icon = AppIcon.MoveFolder,
            action = { send(Msg.Ui.OnBottomBarMoveSelectedNotesClicked) },
            label = text.shared.btnTitleReplace,
        ),
        BaseBottomBarItem(
            icon = AppIcon.MoveTrash,
            action = { send(Msg.Ui.OnBottomBarRemoveSelectedNotesClicked) },
            label = text.shared.btnTitleRemove,
        ),
    )

    Box {
        BottomBarOld(items)
        AnimateFadeInOut(visible = !isEnabledBar.value) { DisabledBottomBarPlaceholder() }
    }
}