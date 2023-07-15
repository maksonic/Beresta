package ru.maksonic.beresta.feature.notes.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberUpdatedState
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_chips.api.ui.ChipFeature
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesListUiState
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.notes.api.ui.SharedNotesUiState
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
    state: NotesListUiState,
    sharedUiState: SharedUiState<SharedNotesUiState>,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    chipsRowOffsetHeightPx: MutableState<Float>,
    noteCard: NotesApi.Ui.Card = koinInject(),
) {

    val sorter = rememberUpdatedState(
        NotesSorter(
            state.collection.data,
            order = listUiSortState.notes.order,
            isSortPinned  = listUiSortState.notes.isSortPinned ,
            sort = listUiSortState.notes.sort,
            currentFolderId = ChipFeature.currentSelectedFolder
        )
    )

    Box {
        Content(
            state = state,
            sorter = sorter,
            noteCard = noteCard,
            sharedUiState = sharedUiState,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
        )
        AnimateFadeInOut(!state.state.isLoading && sorter.value.isEmptyList) {
            ScreenPlaceholder(AppImage.AddNotePlaceholder, text.shared.hintNoNotes)
        }
    }
}
