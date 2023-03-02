package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesListApi {
    interface Ui {
        @Composable
        fun FetchedNotesWidget(
            notes: NoteUi.Collection,
            scrollState: () -> LazyListState,
            modifier: Modifier
        )

        @Composable
        fun EmptyNotesListWidget(modifier: Modifier)

        @Composable
        fun NoteItem(
            onNoteClicked: () -> Unit,
            onNoteLongClicked: () -> Unit,
            note: NoteUi,
            modifier: Modifier
        )
    }
}