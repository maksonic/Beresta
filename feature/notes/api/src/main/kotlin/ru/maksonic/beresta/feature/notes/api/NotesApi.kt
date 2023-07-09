package ru.maksonic.beresta.feature.notes.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiState

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
            val sharedUiState: SharedUiState<SharedNotesUiState>

            @Composable
            fun Widget(
                state: NotesListUiState,
                currentFolderId: Long,
                onNoteClicked: (id: Long) -> Unit,
                onNoteLongClicked: (id: Long) -> Unit,
                chipsRowOffsetHeightPx: MutableState<Float>
            )

            @Composable
            fun Placeholder(gridCellsCount: Int)
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