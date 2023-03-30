package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListSharedScrollState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarApi {

    interface Ui {
        @Composable
        fun Widget(
            notesCollection: NoteUi.Collection,
            sharedScroll: NotesListSharedScrollState
        )

        val sharedExpandSearchState: StateFlow<Boolean>
    }
}