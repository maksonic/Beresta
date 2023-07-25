package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiScrollState

/**
 * @Author maksonic on 01.07.2023
 */
class NotesList : NotesApi.Ui.List {
    override val sharedUiState: SharedUiState<SharedNotesUiScrollState>
        get() = SharedNotesUiScrollState.Initial

    @Composable
    override fun Widget(
        modifier: Modifier,
        placeholderModifier: Modifier,
        state: NotesListUiState,
        sorter: State<NotesSorter>,
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongClicked: (id: Long) -> Unit,
        chipsRowOffsetHeightPx: State<Float>,
        updateChipsRowOffsetHeight: (Float) -> Unit,
        updatedCanScrollBackwardValue: (Boolean) -> Unit,
        contentPaddingValues: PaddingValues
    ) {
        Container(
            modifier = modifier,
            placeholderModifier = placeholderModifier,
            state = state,
            sorter = sorter,
            sharedUiState = sharedUiState,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            updateChipsRowOffsetHeight = updateChipsRowOffsetHeight,
            updatedCanScrollBackwardValue = updatedCanScrollBackwardValue,
            contentPaddingValues = contentPaddingValues
        )
    }

    @Composable
    override fun Placeholder(gridCellsCount: Int, modifier: Modifier) {
        PlaceholderContent(gridCellsCount, modifier)
    }
}


