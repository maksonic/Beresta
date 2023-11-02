package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomAppBar
import ru.maksonic.beresta.common.ui_kit.bar.bottom.BottomBarItem
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.MoveTrash
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_kit.icons.Repartition
import ru.maksonic.beresta.common.ui_kit.icons.Unpin
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.Send

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun BottomBar(model: Model, send: Send) {
    val isDisabledBottomBar = rememberUpdatedState(
        model.notes.collection.data.none { it.isSelected } && model.notes.isSelection
    )
    val isShowUnpinBtn = rememberUpdatedState(model.notes.isVisibleUnpinBottomBarIcon)
    val offset = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)
    val transition = animateDpAsState(
        if (model.notes.isSelection) 0.dp else offset, tween(Theme.animVelocity.common), label = ""
    )
    val items = arrayOf(
        BottomBarItem(
            icon = AppIcon.Repartition,
            label = text.shared.btnTitleUnhide,
            onClick = { send(Msg.Ui.OnBottomBarUnhideSelectedNotesClicked) },
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
            icon = AppIcon.MoveTrash,
            onClick = { send(Msg.Ui.OnBottomBarRemoveSelectedNotesClicked) },
            label = text.shared.btnTitleRemove,
        ),
    )
    SurfacePro(
        tonalElevation = Theme.tonal.level4,
        modifier = Modifier.graphicsLayer { translationY = transition.value.toPx() }
    ) {
        BottomAppBar(items, isDisabled = isDisabledBottomBar.value)
    }
}