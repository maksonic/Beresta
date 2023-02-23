package ru.maksonic.beresta.screen.main.presentation.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.screen.main.presentation.MainBottomPanelItem
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.theme.icons.FolderOpen
import ru.maksonic.beresta.ui.theme.icons.Settings
import ru.maksonic.beresta.ui.theme.icons.SortBy
import ru.maksonic.beresta.ui.theme.icons.Trash
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
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
    isScrollTop: () -> Boolean,
    modifier: Modifier = Modifier
) {

    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val panelHeight = Theme.widgetSize.bottomMainPanelHeight.plus(navBarHeight)
    val panelOffset = animateDpAsState(
        targetValue = if (isScrollTop()) 0.dp else panelHeight,
        animationSpec = tween()
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
                IconAction(icon = { panelItem.icon }, action = { send(panelItem.msg) })
            }
        }
    }
}