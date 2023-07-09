package ru.maksonic.beresta.feature.sorting_sheet.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi

/**
 * @Author maksonic on 27.06.2023
 */
class SortNotesFeatureCore(private val datastore: Datastore) : SortingSheetApi.Feature.State {
    private companion object {
        private val INITIAL_ORDER_STATE = Order.DESCENDING.name
        private val INITIAL_SORT_STATE = Sort.DATE_CREATION.name
        private const val INITIAL_IS_SORT_PINNED = false
        private const val INITIAL_NOTES_GRID_COUNT = 1
    }

    private val orderKey = stringPreferencesKey("prefs_app_sort_order_key")
    private val sortKey = stringPreferencesKey("prefs_app_sort_key")
    private val sortPinnedKey = booleanPreferencesKey("prefs_app_sort_pinned_key")
    private val gridKey = intPreferencesKey("prefs_app_note_grid_count_key")

    override val current: Flow<ListSortUiState> = datastore.datastore.data.map { prefs ->
        val order = Order.valueOf(prefs[orderKey] ?: INITIAL_ORDER_STATE)
        val sort = Sort.valueOf(prefs[sortKey] ?: INITIAL_SORT_STATE)
        val isSortPinned = prefs[sortPinnedKey] ?: INITIAL_IS_SORT_PINNED
        val gridCount = prefs[gridKey] ?: INITIAL_NOTES_GRID_COUNT

        return@map ListSortUiState(
            order = order,
            sort = sort,
            isSortPinned = isSortPinned,
            gridCount = gridCount
        )
    }

    override suspend fun setOrderState(order: Order) {
        datastore.datastore.edit { prefs -> prefs[orderKey] = order.name }
    }

    override suspend fun setSortState(sort: Sort) {
        datastore.datastore.edit { prefs -> prefs[sortKey] = sort.name }
    }

    override suspend fun setCheckboxState(isSortPinned: Boolean) {
        datastore.datastore.edit { prefs -> prefs[sortPinnedKey] = isSortPinned }
    }

    override suspend fun setGridCount(cellCount: Int) {
        datastore.datastore.edit { prefs -> prefs[gridKey] = cellCount }
    }

    override suspend fun resetSortState() {
        datastore.datastore.edit { prefs ->
            prefs[orderKey] = INITIAL_ORDER_STATE
            prefs[sortKey] = INITIAL_SORT_STATE
            prefs[sortPinnedKey] = INITIAL_IS_SORT_PINNED
        }
    }
}