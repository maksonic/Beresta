package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesListApi {
    interface Ui {
        @Composable
        fun FetchedNotesWidget(notes: NotesCollection, modifier: Modifier)

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