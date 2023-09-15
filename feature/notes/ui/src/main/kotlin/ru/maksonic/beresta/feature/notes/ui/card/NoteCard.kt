package ru.maksonic.beresta.feature.notes.ui.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.ui.ext.update
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardElevation
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi

/**
 * @Author maksonic on 22.06.2023
 */
class NoteCard : NotesApi.Card.Ui {
    companion object {
        private const val INITIAL_TITLE_LINES_COUNT = 1
        private const val INITIAL_MESSAGE_LINES_COUNT = 2
    }

    override val sharedState = mutableStateOf(NoteCardUiState.Initial)

    override fun update(state: NoteCardUiState) = sharedState.update(state)

    override fun updateShape(shape: NoteCardShape) =
        sharedState.update(sharedState.value.copy(shape = shape))

    override fun updateElevation(elevation: NoteCardElevation) =
        sharedState.update(sharedState.value.copy(elevation = elevation))

    override fun updateMaxTitleLines(count: Int) =
        sharedState.update(sharedState.value.copy(maxTitleLines = count))

    override fun updateMaxMessageLines(count: Int) =
        sharedState.update(sharedState.value.copy(maxMessageLines = count))

    override fun updateColorMarkerVisibility(isVisible: Boolean) =
        sharedState.update(sharedState.value.copy(isVisibleColorMarker = isVisible))

    override fun resetNoteCardLinesByDefault() = sharedState.update(
        sharedState.value.copy(
            maxTitleLines = INITIAL_TITLE_LINES_COUNT,
            maxMessageLines = INITIAL_MESSAGE_LINES_COUNT
        )
    )

    @Composable
    override fun Widget(
        note: NoteUi,
        isSelected: Boolean,
        onNoteClicked: (Long) -> Unit,
        onNoteLongClicked: (Long) -> Unit,
        modifier: Modifier
    ) {
        val state = remember { sharedState }

        Container(note, state, isSelected, onNoteClicked, onNoteLongClicked, modifier)
    }
}