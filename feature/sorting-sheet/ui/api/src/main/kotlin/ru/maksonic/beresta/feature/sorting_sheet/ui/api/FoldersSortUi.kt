package ru.maksonic.beresta.feature.sorting_sheet.ui.api

import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort

/**
 * @Author maksonic on 17.10.2023
 */
data class FoldersSortUi(
    val order: Order,
    val sort: Sort,
    val isSortPinned: Boolean
) {
    companion object {
        val Default = FoldersSortUi(
            order = Order.DESCENDING,
            sort = Sort.DATE_CREATION,
            isSortPinned = false
        )
    }
}