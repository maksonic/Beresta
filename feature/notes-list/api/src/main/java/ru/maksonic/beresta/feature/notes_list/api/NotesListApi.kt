package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
data class NotesListSharedScrollState @OptIn(ExperimentalFoundationApi::class) constructor(
    val state: () -> LazyStaggeredGridState,
    val isVisibleFirstNote: () -> Boolean,
    val isVisibleFirstNoteOffset: () -> Boolean,
    val isScrollUp: () -> Boolean,
    val isSelectionState: () -> Boolean,
    val gridCellsCount: () -> Int,
)

interface NotesListApi {
    interface Ui {
        val sharedMainBottomBarState: StateFlow<MainBottomBarState>

        @Composable
        fun FetchedNotesWidget(
            notes: NoteUi.Collection,
            chips: FilterChipUi.Collection,
            onNoteClicked: (id: Long) -> Unit,
            onNoteLongPressed: (id: Long) -> Unit,
            onChipFilterClicked: (id: Int) -> Unit,
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