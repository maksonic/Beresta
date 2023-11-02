package ru.maksonic.beresta.screen.folders.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFilterSorter
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listFoldersSortState

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun rememberFoldersSorter(
    data: FolderUi.Collection
): State<FilterDataSorter<FolderUi>> = rememberUpdatedState(
    FoldersFilterSorter(
        list = data.data,
        order = listFoldersSortState.order,
        sort = listFoldersSortState.sort,
        isSortPinned = listFoldersSortState.isSortPinned
    )
)