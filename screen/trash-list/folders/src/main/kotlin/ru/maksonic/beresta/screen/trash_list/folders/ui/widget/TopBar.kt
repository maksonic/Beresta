package ru.maksonic.beresta.screen.trash_list.folders.ui.widget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.folders.core.Model
import ru.maksonic.beresta.screen.trash_list.folders.core.Msg
import ru.maksonic.beresta.screen.trash_list.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.SelectAll
import ru.maksonic.beresta.ui.widget.bar.top.TopAppCollapsingCounterBar
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 12.07.2023
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


    TopAppCollapsingCounterBar(
        scrollBehavior = scrollBehavior,
        isSelectionState = isSelectionState,
        count = selectedCounter,
        idleTitle = text.trash.topBarTitleTrashedFolders,
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
    AnimateFadeInOut(isSelectionState.value) {
        ClickableIcon(icon = AppIcon.SelectAll) { send(Msg.Ui.OnSelectAllFoldersClicked) }
    }
}