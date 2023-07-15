package ru.maksonic.beresta.feature.folders_chips.api.ui

import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.isAscending


/**
 * @Author maksonic on 10.07.2023
 */
class FoldersSorter(
    list: List<FolderUi>,
    order: Order,
    isSortPinned: Boolean,
    private val sort: Sort
) {
    private fun List<FolderUi>.applyStickyItemsSort() = this.sortedWith(
        compareByDescending<FolderUi> { it.isStickyToStart } then compareBy { it.isStickyToEnd }
    )

    private val pinnedSublist = list.filter { it.isPinned }.sortedWith(
        if (isSortPinned) {
            if (order.isAscending) compareBy {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                }
            }
            else compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
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
                }
            }
        } else {
            compareByDescending {
                when (sort) {
                    Sort.ALPHABET -> it.title
                    Sort.DATE_CREATION -> it.dateCreationRaw
                    Sort.DATE_UPDATE -> it.dateLastUpdateRaw
                }
            }
        }
    )

    val sortedList get() = (pinnedSublist + notPinnedSublist).applyStickyItemsSort()
}