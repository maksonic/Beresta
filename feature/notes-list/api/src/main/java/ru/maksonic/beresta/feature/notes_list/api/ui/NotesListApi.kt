package ru.maksonic.beresta.feature.notes_list.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesListApi {
    interface Ui {
        val sharedMainBottomBarState: StateFlow<MainBottomBarState>

        @Composable
        fun FetchedNotesWidget(
            notes: NoteUi.Collection,
            chips: FilterChipUi.Collection,
            selectedNotes: Set<NoteUi>,
            onNoteClicked: (id: Long) -> Unit,
            onNoteLongPressed: (id: Long) -> Unit,
            onChipFilterClicked: (id: Long) -> Unit,
            sharedScroll: NotesListSharedScrollState,
        )

        @Composable
        fun EmptyNotesListWidget(modifier: Modifier)

        @Composable
        fun NoteItem(
            onNoteClicked: (id: Long) -> Unit,
            onNoteLongClicked: (id: Long) -> Unit,
            note: NoteUi,
            modifier: Modifier
        )
    }
}