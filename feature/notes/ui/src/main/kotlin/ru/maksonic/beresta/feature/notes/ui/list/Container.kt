package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.notes.ui.placeholder.PlaceholderContent
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
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    chipsRowOffsetHeightPx: State<Float>,
    updateChipsRowOffsetHeight: (Float) -> Unit,
    updatedCanScrollBackwardValue: (Boolean) -> Unit,
    updateIsScrollUpSharedScrollState: (Boolean) -> Unit,
    contentPaddingValues: PaddingValues,
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
                onNoteClicked = onNoteClicked,
                onNoteLongClicked = onNoteLongClicked,
                chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
                updateChipsRowOffsetHeight = updateChipsRowOffsetHeight,
                updatedCanScrollBackwardValue = updatedCanScrollBackwardValue,
                updateIsScrollUpSharedScrollState = updateIsScrollUpSharedScrollState,
            contentPaddingValues = contentPaddingValues,
            )
        }

        AnimateFadeInOut(!state.state.isLoading && sorter.value.isEmptyList) {
            ScreenPlaceholder(AppImage.AddNotePlaceholder, text.shared.hintNoNotes)
        }
    }
}
