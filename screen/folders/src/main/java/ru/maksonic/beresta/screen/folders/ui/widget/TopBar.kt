package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.folders.core.Model
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.theme.icons.SortBy
import ru.maksonic.beresta.ui.widget.bar.top.TopAppCollapsingCounterBar
import ru.maksonic.beresta.ui.widget.button.ClickableIcon

/**
 * @Author maksonic on 06.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior?,
    model: State<Model>,
    send: SendMessage,
) {
    val isSelectionState = rememberUpdatedState(model.value.isSelectionState)
    val selectedCounter = rememberUpdatedState(model.value.selectedList.count())

    val title = with(text.folders) {
        if (model.value.isMoveNotesToFolder) btnTitleSelectFolder else topBarTitle
    }

    TopAppCollapsingCounterBar(
        scrollBehavior = scrollBehavior,
        isSelectionState = isSelectionState,
        count = selectedCounter,
        idleTitle = title,
        onBackClicked = {
            if (isSelectionState.value) {
                send(Msg.Ui.CancelSelectionState)
            } else {
                send(Msg.Ui.OnTopBarBackPressed)
            }
        },
        actions = { ActionIcon(send, isSelectionState) }
    )
}

@Composable
private fun ActionIcon(send: SendMessage, isSelectionState: State<Boolean>) {
    Crossfade(
        targetState = isSelectionState.value,
        animationSpec = tween(Theme.animSpeed.common)
    ) {
        ClickableIcon(
            icon = if (it) AppIcon.SelectAll else AppIcon.SortBy,
            action = {
                if (isSelectionState.value) {
                    send(Msg.Ui.OnTopBarSelectAllClicked)
                } else {
                    send(Msg.Ui.OnTopBarSortFolderClicked)
                }
            }
        )
    }
}