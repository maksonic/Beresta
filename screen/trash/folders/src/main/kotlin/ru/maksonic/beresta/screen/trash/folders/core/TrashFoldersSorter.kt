package ru.maksonic.beresta.screen.trash.folders.core

import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi

/**
 * @Author maksonic on 01.11.2023
 */
class TrashFoldersSorter(list: List<FolderUi>) : FilterDataSorter<FolderUi> {
    override val sortedByFilterList: List<FolderUi> =
        list.sortedByDescending { it.dateMovedToTrashRaw }

    override val isEmptyList: Boolean get() = sortedByFilterList.isEmpty()

}