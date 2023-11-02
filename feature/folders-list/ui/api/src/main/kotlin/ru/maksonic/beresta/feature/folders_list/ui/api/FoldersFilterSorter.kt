package ru.maksonic.beresta.feature.folders_list.ui.api

import ru.maksonic.beresta.common.core.ui.sorting.FilterDataSorter
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.common.core.ui.sorting.isAscending


/**
 * @Author maksonic on 10.07.2023
 */
class FoldersFilterSorter(
    list: List<FolderUi>,
    order: Order,
    isSortPinned: Boolean,
    private val sort: Sort
) : FilterDataSorter<FolderUi> {

    private fun List<FolderUi>.applyFilter() = this.sortedWith(
        compareByDescending<FolderUi> { it.isStickyToStart } then compareBy { it.isStickyToEnd }
    )

    private val pinnedSublist = list.filter { it.isPinned }.sortedWith(
        if (isSortPinned) {
            if (order.isAscending) compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw

                }
            }
            else compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw

                }
            }
        } else {
            compareByDescending { it.pinTime }
        }
    )

    private val notPinnedSublist = list.filter { !it.isPinned }.sortedWith(
        if (order.isAscending) {
            compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw

                }
            }
        } else {
            compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                    Sort.TRASHED -> it.dateMovedToTrashRaw

                }
            }
        }
    )

    override val sortedByFilterList get() = (pinnedSublist + notPinnedSublist).applyFilter()
    override val isEmptyList = sortedByFilterList.isEmpty()
}