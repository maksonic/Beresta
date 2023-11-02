package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppCollapsingCounterBar
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.SelectAll
import ru.maksonic.beresta.common.ui_kit.icons.SortBy
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.Send

/**
 * @Author maksonic on 06.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    model: Model,
    send: Send,
    scrollBehavior: TopAppBarScrollBehavior?,
) {
    val selectedCounter =
        rememberUpdatedState(model.folders.collection.data.count { it.isSelected })

    val title = with(text.folders) {
        if (model.folders.isNotesMoving) btnTitleSelectFolder else topBarTitle
    }

    TopAppCollapsingCounterBar(
        scrollBehavior = scrollBehavior,
        isSelectionState = model.folders.isSelection,
        count = selectedCounter,
        idleTitle = title,
        onBackClicked = {
            if (model.folders.isSelection) {
                send(Msg.Ui.CancelSelectionState)
            } else {
                send(Msg.Ui.OnTopBarBackPressed)
            }
        },
        actions = {
            ActionIcon(
                send = send,
                isVisible = model.folders.state.successAfterLoading,
                isSelection = model.folders.isSelection
            )
        }
    )
}

@Composable
private fun ActionIcon(send: Send, isVisible: Boolean, isSelection: Boolean) {
    AnimateFadeInOut(isVisible) {
        Crossfade(
            targetState = isSelection,
            animationSpec = tween(Theme.animVelocity.common), label = ""
        ) {
            ButtonIcon(
                icon = if (it) AppIcon.SelectAll else AppIcon.SortBy,
                onClick = {
                    if (isSelection) {
                        send(Msg.Ui.OnTopBarSelectAllClicked)
                    } else {
                        send(Msg.Ui.OnTopBarSortFolderClicked)
                    }
                }
            )
        }
    }
}