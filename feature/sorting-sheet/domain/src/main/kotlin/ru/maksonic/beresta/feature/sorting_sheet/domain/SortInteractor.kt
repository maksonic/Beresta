package ru.maksonic.beresta.feature.sorting_sheet.domain

import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort

/**
 * @Author maksonic on 16.10.2023
 */
interface SortInteractor {
    suspend fun setOrderState(value: Pair<SortDataKey, Order>)
    suspend fun setSortState(value: Pair<SortDataKey, Sort>)
    suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>)
    suspend fun setGridCount(value: Pair<GridCountKey, Int>)
    suspend fun resetSortState(key: SortDataKey)

    class Core(private val repository: SortRepository) : SortInteractor {

        override suspend fun setOrderState(value: Pair<SortDataKey, Order>) =
            repository.setOrderState(value)

        override suspend fun setSortState(value: Pair<SortDataKey, Sort>) =
            repository.setSortState(value)

        override suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>) =
            repository.setCheckboxState(value)

        override suspend fun setGridCount(value: Pair<GridCountKey, Int>) =
            repository.setGridCount(value)

        override suspend fun resetSortState(key: SortDataKey) = repository.resetSortState(key)

    }
}