package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesListApi {
    interface Ui {
        @Composable
        fun FetchedNotesWidget(
            notes: NoteUi.Collection,
            scrollState: () -> LazyListState,
            router: MainScreenRouter,
            modifier: Modifier
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