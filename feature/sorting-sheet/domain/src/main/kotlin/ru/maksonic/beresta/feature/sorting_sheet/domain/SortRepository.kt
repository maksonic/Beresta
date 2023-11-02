package ru.maksonic.beresta.feature.sorting_sheet.domain

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.FoldersSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain

/**
 * @Author maksonic on 16.10.2023
 */
interface SortRepository {
    fun fetchNotesSort(): Flow<NotesSortDomain>
    fun fetchHiddenNotesSort(): Flow<NotesSortDomain>
    fun fetchFoldersSort(): Flow<FoldersSortDomain>
    suspend fun setOrderState(value: Pair<SortDataKey, Order>)
    suspend fun setSortState(value: Pair<SortDataKey, Sort>)
    suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>)
    suspend fun setGridCount(value: Pair<GridCountKey, Int>)
    suspend fun resetSortState(key: SortDataKey)
}