package ru.maksonic.beresta.feature.notes.ui.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi

/**
 * @Author maksonic on 22.06.2023
 */
class NoteCard(override val state: SharedUiState<NoteCardUiState>) : NotesApi.Ui.Card {

    @Composable
    override fun Widget(
        note: NoteUi,
        isSelected: Boolean,
        onNoteClicked: (Long) -> Unit,
        onNoteLongClicked: (Long) -> Unit,
        modifier: Modifier
    ) {
        val state = state.state.collectAsState()

        Container(note, state, isSelected, onNoteClicked, onNoteLongClicked, modifier)
    }
}