package ru.maksonic.beresta.screen.main.presentation.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.screen.main.presentation.MainBottomPanelItem
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 21.02.2023
 */
private val actions = arrayOf(
    MainBottomPanelItem(icon = AppIcon.Settings, msg = Msg.Ui.OnSettingsClicked),
    MainBottomPanelItem(icon = AppIcon.Trash, msg = Msg.Ui.OnTrashClicked),
    MainBottomPanelItem(icon = AppIcon.FolderOpen, msg = Msg.Ui.OnOpenFoldersClicked),
    MainBottomPanelItem(icon = AppIcon.SortBy, msg = Msg.Ui.OnSortNotesByClicked),
)

@Composable
fun MainBottomIdlePanelWidget(
    send: SendMessage,
    isScrollUp: () -> Boolean,
    modifier: Modifier = Modifier
) {

    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val panelHeight = Theme.widgetSize.bottomMainPanelHeight.plus(navBarHeight)
    val panelOffset = animateDpAsState(
        targetValue = if (isScrollUp()) 0.dp else panelHeight, animationSpec = tween()
    )

    Column(
        modifier
            .fillMaxWidth()
            .height(panelHeight)
            .graphicsLayer {
                translationY = panelOffset.value.toPx()
            }
            .background(tertiaryContainer)
            .noRippleClickable {}
    ) {
        Row(
            modifier
                .padding(start = dp4)
                .height(Theme.widgetSize.bottomMainPanelHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions.forEach { panelItem ->
                IconAction(
                    icon = { panelItem.icon },
                    action = { send(panelItem.msg) },
                    tint = onTertiary
                )
            }
        }
    }
}