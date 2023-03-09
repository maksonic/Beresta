package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.SendMessage
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.editor.EditNoteIdlePanelWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.editor.EditorPanelState

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
            .statusBarsPadding()
            .fillMaxSize()
            .imePadding()
    ) {
        EditNoteTopBarWidget(backPressed = onTopBarBackPressed, menuPressed = { })
        Spacer(modifier.weight(1f))
        EditNoteIdlePanelWidget(
            send = send,
            state = editorPanelState,
            isScrollUp = { isVisibleEditorPanel })
    }
}