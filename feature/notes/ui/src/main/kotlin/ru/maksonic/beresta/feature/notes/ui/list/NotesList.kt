package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiState

/**
 * @Author maksonic on 01.07.2023
 */
class NotesList : NotesApi.Ui.List {
    override val sharedUiState: SharedUiState<SharedNotesUiState>
        get() = SharedNotesUiState.Initial

    @Composable
    override fun Widget(
        state: NotesListUiState,
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongClicked: (id: Long) -> Unit,
        chipsRowOffsetHeightPx: MutableState<Float>
    ) {
        Container(
            state = state,
            sharedUiState = sharedUiState,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx
        )
    }

    @Composable
    override fun Placeholder(gridCellsCount: Int, modifier: Modifier) {
        PlaceholderContent(gridCellsCount, modifier)
    }
}


