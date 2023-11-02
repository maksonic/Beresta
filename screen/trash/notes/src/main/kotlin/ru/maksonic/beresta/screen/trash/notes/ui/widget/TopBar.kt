package ru.maksonic.beresta.screen.trash.notes.ui.widget

import androidx.compose.animation.Crossfade
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppCollapsingCounterBar
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.RemoveFolder
import ru.maksonic.beresta.common.ui_kit.icons.SelectAll
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash.notes.core.Model
import ru.maksonic.beresta.screen.trash.notes.core.Msg
import ru.maksonic.beresta.screen.trash.notes.ui.Send

/**
 * @Author maksonic on 06.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(model: Model, send: Send, scrollBehavior: TopAppBarScrollBehavior?) {
    val selectedCounter = rememberUpdatedState(model.notes.data.count { it.isSelected })

    TopAppCollapsingCounterBar(
        scrollBehavior = scrollBehavior,
        isSelectionState = model.isSelection,
        count = selectedCounter,
        idleTitle = text.trash.topBarTitleTrash,
        onBackClicked = {
            if (model.isSelection) {
                send(Msg.Ui.CancelSelectionState)
            } else {
                send(Msg.Ui.OnTopBarBackPressed)
            }
        },
        actions = { ActionIcon(send, model.isSelection) }
    )
}

@Composable
private fun ActionIcon(send: Send, isSelection: Boolean) {
    Crossfade(isSelection, label = "") {
        ButtonIcon(
            icon = if (it) AppIcon.SelectAll else AppIcon.RemoveFolder,
            onClick = {
                if (it) {
                    send(Msg.Ui.OnSelectAllNotesClicked)
                } else {
                    send(Msg.Ui.OnTrashedFoldersBtnClicked)
                }
            }
        )
    }
}