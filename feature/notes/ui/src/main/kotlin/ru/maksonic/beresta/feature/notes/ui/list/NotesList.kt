package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.ui.ext.update
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter

/**
 * @Author maksonic on 01.07.2023
 */
class NotesList : NotesApi.List.Ui {
    override val isScrollUpSharedState = mutableStateOf(false)
    override fun updateScrollState(isScrollUp: Boolean) = isScrollUpSharedState.update(isScrollUp)

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
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            updateChipsRowOffsetHeight = updateChipsRowOffsetHeight,
            updatedCanScrollBackwardValue = updatedCanScrollBackwardValue,
            updateIsScrollUpSharedScrollState = { isScrollUp -> updateScrollState(isScrollUp) },
            contentPaddingValues = contentPaddingValues
        )
    }
}


