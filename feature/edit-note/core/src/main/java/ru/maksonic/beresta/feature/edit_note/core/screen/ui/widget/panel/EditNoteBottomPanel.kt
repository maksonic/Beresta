package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.SendMessage
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.isEmpty
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Save
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.FloatingFabButton

/**
 * @Author maksonic on 06.03.2023
 */
@Composable
internal fun EditNoteBottomPanel(
    send: SendMessage,
    currentNote: NoteUi,
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
        val fabElevation =
            animateDpAsState(if (isScrollUp()) Theme.tonal.Level0 else Theme.tonal.Level3)
        val fabUnselectedColor = tertiaryContainer.copy(alpha = 0.35f).compositeOver(background)
        val saveNoteFabColor =
            animateColorAsState(
                if (currentNote.isEmpty()) fabUnselectedColor else tertiaryContainer)
        val fabIconColor =
            animateColorAsState(if (currentNote.isEmpty()) Theme.color.black.copy(alpha = 0.4f)
            else onTertiaryContainer)

        SurfacePro(
            tonalElevation = Theme.tonal.Level4,
            modifier = panelModifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = panelOffset.value.toPx()
                }
        ) {
            Column(panelModifier.padding(start = dp4)) {
                when (state) {
                    EditorPanelState.IDLE -> {
                        EditNoteIdlePanelContent(send, panelHeight, navBarHeight, panelModifier)
                    }
                    EditorPanelState.SELECT_WALLPAPER -> {}
                }
            }
        }

        FloatingFabButton(
            onClick = { send(Msg.Inner.OnSaveNoteClicked(currentNote)) },
            enabled = !currentNote.isEmpty(),
            fabColor = saveNoteFabColor.value,
            shadowElevation = fabElevation.value,
            modifier = modifier.padding(top = dp12, end = dp16)
        ) {
            Icon(
                imageVector = AppIcon.Save,
                contentDescription = "",
                tint = fabIconColor.value
            )
        }

    }
}