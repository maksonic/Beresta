package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.SendMessage
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel.EditNoteBottomPanel
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel.EditorPanelState
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.SystemStatusBar

/**
 * @Author maksonic on 09.03.2023
 */
@Composable
internal fun TopBarWithEditorPanelContainer(
    send: SendMessage,
    editorPanelState: EditorPanelState,
    onTopBarBackPressed: () -> Unit,
    isVisibleEditorPanel: Boolean,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
    ) {
        val statusBarColor = background.copy(alpha = 0.5f)
        SystemStatusBar(backgroundColor = { statusBarColor })

        EditNoteTopBarWidget(backPressed = onTopBarBackPressed, menuPressed = { })

        Spacer(modifier.weight(1f))

        EditNoteBottomPanel(
            send = send,
            state = editorPanelState,
            isScrollUp = { isVisibleEditorPanel }
        )
    }
}