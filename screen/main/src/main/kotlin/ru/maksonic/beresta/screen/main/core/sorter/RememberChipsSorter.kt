package ru.maksonic.beresta.screen.main.core.sorter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFilterSorter
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listFoldersSortState
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState

/**
 * @Author maksonic on 17.10.2023
 */
@Composable
internal fun rememberChipsSorter(chips: List<FolderUi>) = rememberUpdatedState(
    FoldersFilterSorter(
        list = chips,
        order = listFoldersSortState.order,
        isSortPinned = listFoldersSortState.isSortPinned,
        sort = listFoldersSortState.sort
    )
)