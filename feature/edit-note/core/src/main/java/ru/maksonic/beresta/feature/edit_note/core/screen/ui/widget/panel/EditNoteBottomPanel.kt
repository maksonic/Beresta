package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.screen.PanelItem
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.onTertiary
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 06.03.2023
 */
@Composable
internal fun EditNoteBottomPanel(
    send: SendMessage,
    isScrollUp: () -> Boolean,
    state: EditorPanelState,
    modifier: Modifier = Modifier
) {

    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val panelHeight = Theme.widgetSize.bottomMainPanelHeight
    val panelOffset = animateDpAsState(
        targetValue = if (isScrollUp()) 0.dp else panelHeight.plus(navBarHeight),
        animationSpec = tween()
    )

    val keyboardHeight = WindowInsets.Companion.ime.asPaddingValues().calculateBottomPadding()
    val calculatedPanelPadding = if (keyboardHeight != 0.dp && keyboardHeight > 100.dp)
        keyboardHeight - navBarHeight
    else
        keyboardHeight

    Box(
        modifier
            .padding(PaddingValues(bottom = calculatedPanelPadding))
            .fillMaxWidth()
            .height(panelHeight.plus(navBarHeight)),
        contentAlignment = Alignment.TopEnd
    ) {
        val panelModifier = Modifier

        Column(
            panelModifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = panelOffset.value.toPx()
                }
                .background(tertiaryContainer)
                .padding(start = dp4),
        ) {
            when (state) {
                EditorPanelState.IDLE -> {
                    EditNoteIdlePanelContent(send, panelHeight, navBarHeight, panelModifier)
                }
                EditorPanelState.SELECT_WALLPAPER -> {}
            }
        }

        FloatingActionButton(
            onClick = {},
            containerColor = primary,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = Theme.elevation.Level0
            ),
            modifier = Modifier.padding(top = dp12, end = dp16)
        ) {
            Icon(imageVector = AppIcon.Save, contentDescription = "", tint = onPrimary)
        }
    }
}

