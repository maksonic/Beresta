package ru.maksonic.beresta.feature.notes.api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardElevation
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiScrollState

/**
 * @Author maksonic on 22.06.2023
 */
interface NotesApi {

    interface Ui {
        interface Card {
            val state: SharedUiState<NoteCardUiState>

            @Composable
            fun Widget(
                note: NoteUi,
                isSelected: Boolean,
                onNoteClicked: (Long) -> Unit,
                onNoteLongClicked: (Long) -> Unit,
                modifier: Modifier
            )
        }

        interface List {
            val sharedUiState: SharedUiState<SharedNotesUiScrollState>

            @Composable
            fun Widget(
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
            )

            @Composable
            fun Placeholder(gridCellsCount: Int, modifier: Modifier)
        }
    }

    interface Feature {
        interface NoteCardState {
            val current: Flow<NoteCardUiState>
            suspend fun setCardShape(shape: NoteCardShape)
            suspend fun setCardElevation(elevation: NoteCardElevation)
            suspend fun setCardTitleMaxLines(value: Int)
            suspend fun setCardMessageMaxLines(value: Int)
            suspend fun setByDefaultCardMaxLines()
        }
    }
}