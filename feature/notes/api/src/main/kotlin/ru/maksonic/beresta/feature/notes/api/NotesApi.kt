package ru.maksonic.beresta.feature.notes.api

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardElevation
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter

/**
 * @Author maksonic on 22.06.2023
 */
interface NotesApi {
    interface Card {
        interface Ui {
            val sharedState: State<NoteCardUiState>
            fun update(state: NoteCardUiState)
            fun updateShape(shape: NoteCardShape)
            fun updateElevation(elevation: NoteCardElevation)
            fun updateMaxTitleLines(count: Int)
            fun updateMaxMessageLines(count: Int)
            fun resetNoteCardLinesByDefault()

            @Composable
            fun Widget(
                note: NoteUi,
                isSelected: Boolean,
                onNoteClicked: (Long) -> Unit,
                onNoteLongClicked: (Long) -> Unit,
                modifier: Modifier
            )
        }
    }

    interface List {
        interface Ui {
            val isScrollUpSharedState: State<Boolean>
            fun updateScrollState(isScrollUp: Boolean)

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
        }
    }

    interface ListPlaceholder {
        interface Ui {
            @Composable
            fun Placeholder(gridCellsCount: Int, modifier: Modifier)
        }
    }

    interface CardStateStorage {
        val current: Flow<NoteCardUiState>
        suspend fun setCardShape(shape: NoteCardShape)
        suspend fun setCardElevation(elevation: NoteCardElevation)
        suspend fun setCardTitleMaxLines(value: Int)
        suspend fun setCardMessageMaxLines(value: Int)
        suspend fun setByDefaultCardMaxLines()
    }
}