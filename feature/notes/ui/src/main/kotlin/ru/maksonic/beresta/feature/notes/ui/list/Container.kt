package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiScrollState
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.images.AddNotePlaceholder
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun Container(
    modifier: Modifier,
    placeholderModifier: Modifier,
    state: NotesListUiState,
    sorter: State<NotesSorter>,
    sharedUiState: SharedUiState<SharedNotesUiScrollState>,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    chipsRowOffsetHeightPx: State<Float>,
    updateChipsRowOffsetHeight: (Float) -> Unit,
    updatedCanScrollBackwardValue: (Boolean) -> Unit,
    contentPaddingValues: PaddingValues,
    noteCard: NotesApi.Ui.Card = koinInject(),
) {
    val gridCells =
        with(listUiSortState) { if (state.isHidden) gridHiddenNotesCount else gridNotesCount }

    Box {
        if (state.state.isLoading) {
            PlaceholderContent(gridCellsCount = gridCells, placeholderModifier)
        }
        if (state.state.successAfterLoading) {
            Content(
                modifier = modifier,
                state = state,
                sorter = sorter,
                noteCard = noteCard,
                sharedUiState = sharedUiState,
                onNoteClicked = onNoteClicked,
                onNoteLongClicked = onNoteLongClicked,
                chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
                updateChipsRowOffsetHeight = updateChipsRowOffsetHeight,
                updatedCanScrollBackwardValue = updatedCanScrollBackwardValue,
                contentPaddingValues = contentPaddingValues,
            )
        }

        AnimateFadeInOut(!state.state.isLoading && sorter.value.isEmptyList) {
            ScreenPlaceholder(AppImage.AddNotePlaceholder, text.shared.hintNoNotes)
        }
    }
}
