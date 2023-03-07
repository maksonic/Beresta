package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.screen.PanelItem
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AddImage
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Save
import ru.maksonic.beresta.ui.theme.icons.VoiceEnter
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 06.03.2023
 */
private val idlePanelItems = arrayOf(
    PanelItem(icon = AppIcon.VoiceEnter, msg = Msg.Ui.OnTopBarBackPressed),
    PanelItem(icon = AppIcon.AddImage, msg = Msg.Ui.OnTopBarBackPressed),
)

@Composable
fun EditNoteIdlePanelWidget(send: SendMessage, isScrollUp: () -> Boolean) {

    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val panelHeight = Theme.widgetSize.bottomMainPanelHeight
    val panelOffset = animateDpAsState(
        targetValue = if (isScrollUp()) 0.dp else panelHeight.plus(navBarHeight),
        animationSpec = tween()
    )

    Box(
        Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(panelHeight),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(panelHeight)
                .graphicsLayer {
                    translationY = panelOffset.value.toPx()
                }
                .background(tertiaryContainer)
                .padding(start = dp4)
                .noRippleClickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            idlePanelItems.forEach { panelItem ->
                IconAction(
                    icon = { panelItem.icon },
                    action = { send(panelItem.msg) },
                    tint = onTertiary
                )
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(panelHeight),
            contentAlignment = Alignment.CenterEnd
        ) {
            FloatingActionButton(
                onClick = {},
                containerColor = primary,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = Theme.elevation.disable
                ),
                modifier = Modifier.padding(end = dp16)
            ) {
                Icon(imageVector = AppIcon.Save, contentDescription = "", tint = onPrimary)
            }
        }
    }
}
