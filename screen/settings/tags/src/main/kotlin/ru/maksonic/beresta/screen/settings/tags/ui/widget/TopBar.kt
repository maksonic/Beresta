package ru.maksonic.beresta.screen.settings.tags.ui.widget

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppCollapsingCounterBar
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.SelectAll
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.tags.core.Model
import ru.maksonic.beresta.screen.settings.tags.core.Msg
import ru.maksonic.beresta.screen.settings.tags.ui.Send

/**
 * @Author maksonic on 11.11.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(model: Model, send: Send, scrollBehavior: TopAppBarScrollBehavior?) {
    TopAppCollapsingCounterBar(
        scrollBehavior = scrollBehavior,
        isSelectionState = model.isSelection,
        count = rememberUpdatedState(model.tags.data.count { it.isSelected }),
        idleTitle = text.tags.topBarTitleTagsManagement,
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
    AnimateFadeInOut(isSelection) {
        ButtonIcon(
            icon = AppIcon.SelectAll,
            onClick = { send(Msg.Ui.OnSelectAllTagsClicked) }
        )
    }
}