package ru.maksonic.beresta.screen.hidden_notes.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesFilterSorter
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listHiddenNotesSortState

/**
 * @Author maksonic on 21.10.2023
 */
@Composable
fun rememberHiddenNotesSorter(collection: NoteUi.Collection) = rememberUpdatedState(
    NotesFilterSorter(
        list = collection.data,
        order = listHiddenNotesSortState.order,
        isSortPinned = listHiddenNotesSortState.isSortPinned,
        sort = listHiddenNotesSortState.sort,
        currentFolderId = 1
    )
)
