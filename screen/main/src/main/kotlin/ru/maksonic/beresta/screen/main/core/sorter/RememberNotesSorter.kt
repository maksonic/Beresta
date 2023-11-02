package ru.maksonic.beresta.screen.main.core.sorter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesFilterSorter
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
internal fun rememberNotesSorter(notes: List<NoteUi>) = rememberUpdatedState(
    NotesFilterSorter(
        list = notes,
        order = listNotesSortState.order,
        isSortPinned = listNotesSortState.isSortPinned,
        sort = listNotesSortState.sort,
        currentFolderId = FoldersFeature.currentSelected
    )
)