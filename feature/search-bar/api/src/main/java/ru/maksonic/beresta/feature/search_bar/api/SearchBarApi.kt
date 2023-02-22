package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarApi {

    interface Ui {
        @Composable
        fun Widget(notesCollection: NotesCollection, modifier: Modifier)
    }
}