package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarApi {

    interface Ui {
        @Composable
        fun Widget(
            notesCollection: NoteUi.Collection,
            isVisibleFirstNote: () -> Boolean,
            modifier: Modifier
        )

        val searchBarVisibilityState: MutableStateFlow<Boolean>
    }
}