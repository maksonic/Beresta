package ru.maksonic.beresta.feature.notes_list.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesListApi {
    interface Ui {

        @Composable
        fun FetchedNotesWidget(
            notes: NoteUi.Collection,
            chips: FilterChipUi.Collection,
            selectedNotes: Set<NoteUi>,
            currentSelectedChipId: Long,
            onNoteClicked: (id: Long) -> Unit,
            onNoteLongPressed: (id: Long) -> Unit,
            onChipFilterClicked: (id: Long) -> Unit,
            sharedScroll: NotesListSharedScrollState,
        )

        @Composable
        fun EmptyNotesListWidget(modifier: Modifier)

        @Composable
        fun NoteItem(
            note: NoteUi,
            selectedNotes: Set<NoteUi>,
            onNoteClicked: (id: Long) -> Unit,
            onNoteLongClicked: (id: Long) -> Unit,
            modifier: Modifier
        )
    }
}