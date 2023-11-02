package ru.maksonic.beresta.feature.sorting_sheet.domain.sort

import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort

/**
 * @Author maksonic on 17.10.2023
 */
data class NotesSortDomain(
    val order: Order,
    val sort: Sort,
    val isSortPinned: Boolean,
    val gridCount: Int
) {
    companion object {
        val Default = NotesSortDomain(
            order = Order.DESCENDING,
            sort = Sort.DATE_CREATION,
            isSortPinned = false,
            gridCount = 1
        )
    }
}