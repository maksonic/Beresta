package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomAppBar
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomBarItem
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.FolderOpen
import ru.maksonic.beresta.common.ui_kit.icons.Lock
import ru.maksonic.beresta.common.ui_kit.icons.MoveFolder
import ru.maksonic.beresta.common.ui_kit.icons.MoveTrash
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_kit.icons.Settings
import ru.maksonic.beresta.common.ui_kit.icons.SortBy
import ru.maksonic.beresta.common.ui_kit.icons.Trash
import ru.maksonic.beresta.common.ui_kit.icons.Unpin
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.screen.Send

/**
 * @Author maksonic on 22.06.2023
 */
enum class MainBottomBarState {
    IDLE, SELECTION
}

@Composable
internal fun MainBottomBar(
    model: Model,
    send: Send,
    state: State<MainBottomBarState>,
    isVisibleBottomBar: State<Boolean>,
) {
    val animSpeed = Theme.animVelocity.common

    val isVisible = rememberUpdatedState(
        if (model.notes.isSelection) true else isVisibleBottomBar.value
    )

    val offset = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)
    val transition = animateDpAsState(
        if (isVisible.value) 0.dp else offset, tween(Theme.animVelocity.common), label = ""
    )

    SurfacePro(
        tonalElevation = Theme.tonal.level4,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { translationY = transition.value.toPx() }
    ) {
        Column(Modifier.fillMaxWidth()) {
            AnimatedContent(targetState = state.value, transitionSpec = {
                (fadeIn(animationSpec = tween(animSpeed, delayMillis = 90)) +
                        scaleIn(
                            initialScale = 0.92f,
                            animationSpec = tween(animSpeed, delayMillis = 90)
                        ))
                    .togetherWith(fadeOut(animationSpec = tween(90)))
            }, label = "") { barState ->
                when (barState) {
                    MainBottomBarState.IDLE -> IdleBarContent(send)
                    MainBottomBarState.SELECTION -> SelectedBarContent(model, send)
                }
            }
        }
    }
}

@Composable
private fun IdleBarContent(
    send: Send,
    modifier: Modifier = Modifier
) {
    val items = arrayOf(
        BottomBarItem(AppIcon.Settings, { send(Msg.Ui.OnBottomBarSettingsClicked) }),
        BottomBarItem(AppIcon.Trash, { send(Msg.Ui.OnBottomBarTrashClicked) }),
        BottomBarItem(AppIcon.FolderOpen, { send(Msg.Ui.OnBottomBarFoldersClicked) }),
        BottomBarItem(AppIcon.SortBy, { send(Msg.Ui.OnBottomBarSortNotesClicked) }),
    )

    Row(
        modifier
            .navigationBarsPadding()
            .padding(start = dp4)
            .height(Theme.size.bottomMainBarHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { panelItem ->
            ButtonIcon(icon = panelItem.icon, onClick = panelItem.onClick, tint = onSurface)
        }
    }
}

@Composable
private fun SelectedBarContent(model: Model, send: Send) {
    val isDisabledBottomBar = rememberUpdatedState(
        model.notes.collection.data.none { it.isSelected } && model.notes.isSelection
    )
    val isShowUnpinBtn = rememberUpdatedState(model.notes.isVisibleUnpinBottomBarIcon)
    val items = arrayOf(
        BottomBarItem(
            icon = AppIcon.Lock,
            onClick = { send(Msg.Ui.OnBottomBarHideSelectedNotesClicked) },
            label = text.shared.btnTitleHide,
        ),
        BottomBarItem(
            label = if (isShowUnpinBtn.value) text.shared.btnTitleUnpin
            else text.shared.btnTitlePin,
            icon = if (isShowUnpinBtn.value) AppIcon.Unpin else AppIcon.Pin,
            onClick = {
                send(Msg.Ui.OnBottomBarPinSelectedNotesClicked)
                send(Msg.Ui.CancelNotesSelection)
            }),
        BottomBarItem(
            icon = AppIcon.MoveFolder,
            onClick = { send(Msg.Ui.OnBottomBarMoveSelectedNotesClicked) },
            label = text.shared.btnTitleReplace,
        ),
        BottomBarItem(
            icon = AppIcon.MoveTrash,
            onClick = { send(Msg.Ui.OnBottomBarRemoveSelectedNotesClicked) },
            label = text.shared.btnTitleRemove,
        ),
    )

    BottomAppBar(items, isDisabled = isDisabledBottomBar.value)
}

