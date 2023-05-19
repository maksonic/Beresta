package ru.maksonic.beresta.feature.edit_note.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import ru.maksonic.beresta.feature.edit_note.core.Model
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.ui.widget.EditNoteTopBar
import ru.maksonic.beresta.feature.edit_note.core.ui.widget.EditorBottomBar
import ru.maksonic.beresta.feature.edit_note.core.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.notes.list.api.ui.isBlank
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.widget.functional.isScrollUp

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun EditNoteExpandedContent(
    model: Model,
    send: SendMessage,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {

    Box(
        modifier
            .fillMaxSize()
            .statusBarsPadding()
            .imePadding()
    ) {
        val scrollState = rememberLazyListState()
        val isScrollUp = scrollState.isScrollUp()

        LazyColumn(
            state = scrollState,
            contentPadding = PaddingValues(bottom = Theme.widgetSize.bottomMainBarHeight.plus(dp10))
        ) {
            item {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(Theme.widgetSize.topBarNormalHeight)
                )
            }

            item {
                NoteTitleInputFieldWidget(model.currentNote.title, send, focusRequester)
            }

            item {
                NoteMessageInputFieldWidget(model.currentNote.message, send)
            }
        }
        OverContentLayer(send, isScrollUp = isScrollUp.value, isBlankNote = model.currentNote.isBlank())
    }
}

@Composable
private fun OverContentLayer(send: SendMessage, isScrollUp: Boolean, isBlankNote: Boolean) {
    Column {
        EditNoteTopBar(
            backAction = { send(Msg.Ui.OnTopBarBackPressed) },
            menuAction = {}
        )
        Spacer(Modifier.weight(1f))
        EditorBottomBar(send = send, isScrollUp = isScrollUp, isBlankNote = isBlankNote)
    }
}