package ru.maksonic.beresta.feature.sorting_sheet.data

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.feature.sorting_sheet.domain.GridCountKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortRepository
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.FoldersSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain

/**
 * @Author maksonic on 16.10.2023
 */
class SortRepositoryImpl(private val sortDataStore: SortDataStore) : SortRepository {
    override fun fetchNotesSort(): Flow<NotesSortDomain> = sortDataStore.fetchNotesSort()
    override fun fetchHiddenNotesSort(): Flow<NotesSortDomain> =
        sortDataStore.fetchHiddenNotesSort()

    override fun fetchFoldersSort(): Flow<FoldersSortDomain> = sortDataStore.fetchFoldersSort()

    override suspend fun setOrderState(value: Pair<SortDataKey, Order>) =
        sortDataStore.setOrderState(value)

    override suspend fun setSortState(value: Pair<SortDataKey, Sort>) =
        sortDataStore.setSortState(value)

    override suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>) =
        sortDataStore.setCheckboxState(value)

    override suspend fun setGridCount(value: Pair<GridCountKey, Int>) =
        sortDataStore.setGridCount(value)

    override suspend fun resetSortState(key: SortDataKey) = sortDataStore.resetSortState(key)
}