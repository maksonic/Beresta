package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.feature.edit_note.ui.Model
import ru.maksonic.beresta.feature.edit_note.ui.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.EditNoteTopBar
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.EditorBottomBar
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.notes.api.ui.isBlank

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun ExpandedContent(
    model: Model,
    send: SendMessage,
    focusRequester: FocusRequester,
    isHiddenNote: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .statusBarsPadding()
            .imePadding()
    ) {
        val scrollState = rememberScrollState()
        val isScrollUp = remember { mutableStateOf(true) }
        val canScrollBackward = rememberUpdatedState(scrollState.canScrollBackward)
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val z = available.y
                    val zz = z + available.y
                    isScrollUp.value = zz >= available.y
                    return super.onPreScroll(available, source)
                }
            }
        }

        Column(
            modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(scrollState)

        ) {
            NoteTitleInputFieldWidget(model.currentNote.title, send, focusRequester)
            NoteMessageInputFieldWidget(model.currentNote.message, send)
        }

        ControlPanels(
            send,
            isScrollUp,
            canScrollBackward,
            model.currentNote.isBlank(),
            isHiddenNote
        )
    }
}

@Composable
private fun ControlPanels(
    send: SendMessage,
    isScrollUp: State<Boolean>,
    canScrollBackward: State<Boolean>,
    isBlankNote: Boolean,
    isHiddenNote: Boolean
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        EditNoteTopBar(
            isScrollUp = isScrollUp,
            canScrollBackward = canScrollBackward,
            backAction = { send(Msg.Ui.OnTopBarBackPressed) },
            menuAction = {}
        )

        EditorBottomBar(
            send = send,
            isScrollUp = isScrollUp,
            isBlankNote = isBlankNote,
            isHiddenNote = isHiddenNote
        )
    }
}
