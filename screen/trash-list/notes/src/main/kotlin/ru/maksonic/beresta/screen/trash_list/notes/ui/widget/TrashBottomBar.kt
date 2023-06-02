package ru.maksonic.beresta.screen.trash_list.notes.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.notes.core.Msg
import ru.maksonic.beresta.screen.trash_list.notes.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Delete
import ru.maksonic.beresta.ui.theme.icons.Restart
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.bar.BottomRippleBar
import ru.maksonic.beresta.ui.widget.bar.DisabledBottomBarPlaceholder
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset

/**
 * @Author maksonic on 30.05.2023
 */
@Composable
internal fun TrashBottomBar(
    send: SendMessage,
    isSelectionState: Boolean,
    scrollState: LazyListState,
    isDisabledBottomBar: Boolean,
    modifier: Modifier = Modifier
) {

    val isVisibleFirstItemOffset = scrollState.isVisibleFirstItemOffset()
    val offset = Theme.widgetSize.bottomBarNormalHeight.plus(SystemNavigationBarHeight)
    val panelOffset = animateDp(if (isSelectionState) 0.dp else offset)
    val actions = arrayOf(
        BaseBottomBarItem(
            label = text.shared.btnTitleRestore,
            icon = AppIcon.Restart,
            action = {
                send(Msg.Ui.CancelSelectionState)
            }),
        BaseBottomBarItem(
            label = text.shared.btnTitleDelete,
            icon = AppIcon.Delete,
            action = { send(Msg.Ui.OnSelectAllNotesClicked) }
        )
    )
    val tonal = animateDp(
        if (isVisibleFirstItemOffset.value) Theme.tonal.Level0 else Theme.tonal.Level4
    )

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.graphicsLayer { translationY = panelOffset.value.toPx() }
    ) {
        BottomAppBar(
            modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
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

    AnimateFadeInOut(isDisabledBottomBar) {
        DisabledBottomBarPlaceholder()
    }
}