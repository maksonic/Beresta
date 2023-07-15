package ru.maksonic.beresta.feature.sorting_sheet.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.sorting_sheet.api.CommonSort
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.api.isNotes

/**
 * @Author maksonic on 27.06.2023
 */
class SortNotesFeatureCore(private val datastore: Datastore) : SortingSheetApi.Feature.State {
    private companion object {
        private val INITIAL_ORDER_STATE = Order.DESCENDING.name
        private val INITIAL_SORT_STATE = Sort.DATE_CREATION.name
        private const val INITIAL_IS_SORT_PINNED = false
        private const val INITIAL_NOTES_GRID_COUNT = 1
        private const val BASE_ORDER_KEY = "prefs_app_key_order"
        private const val BASE_SORT_KEY = "prefs_app_key_sort"
        private const val BASE_SORT_PINNED_KEY = "prefs_app_key_sort_pinned"
        private const val NOTES = "_notes"
        private const val FOLDERS = "_folders"
    }

    private val gridKey = intPreferencesKey("prefs_app_note_grid_count_key")
    private val orderNotesKey = stringPreferencesKey(BASE_ORDER_KEY + NOTES)
    private val sortNotesKey = stringPreferencesKey(BASE_SORT_KEY + NOTES)
    private val sortPinnedNotesKey = booleanPreferencesKey(BASE_SORT_PINNED_KEY + NOTES)
    private val orderFoldersKey = stringPreferencesKey(BASE_ORDER_KEY + FOLDERS)
    private val sortFoldersKey = stringPreferencesKey(BASE_SORT_KEY + FOLDERS)
    private val sortPinnedFoldersKey = booleanPreferencesKey(BASE_SORT_PINNED_KEY + FOLDERS)

    override fun current(key: SortDataKey): Flow<ListSortUiState> {
        return datastore.datastore.data.map { prefs ->
            val gridCount = prefs[gridKey] ?: INITIAL_NOTES_GRID_COUNT

            val notes = CommonSort(
                order = Order.valueOf(prefs[orderNotesKey] ?: INITIAL_ORDER_STATE),
                sort = Sort.valueOf(prefs[sortNotesKey] ?: INITIAL_SORT_STATE),
                isSortPinned = prefs[sortPinnedNotesKey] ?: INITIAL_IS_SORT_PINNED
            )

            val folders = CommonSort(
                order = Order.valueOf(prefs[orderFoldersKey] ?: INITIAL_ORDER_STATE),
                sort = Sort.valueOf(prefs[sortFoldersKey] ?: INITIAL_SORT_STATE),
                isSortPinned = prefs[sortPinnedFoldersKey] ?: INITIAL_IS_SORT_PINNED
            )
            return@map ListSortUiState(
                notes = notes,
                folders = folders,
                gridCount = gridCount
            )
        }
    }

    override suspend fun setOrderState(value: Pair<SortDataKey, Order>) {
        val orderKey = if (value.first.isNotes) orderNotesKey else orderFoldersKey
        datastore.datastore.edit { prefs -> prefs[orderKey] = value.second.name }
    }

    override suspend fun setSortState(value: Pair<SortDataKey, Sort>) {
        val sortKey = if (value.first.isNotes) sortNotesKey else sortFoldersKey
        datastore.datastore.edit { prefs -> prefs[sortKey] = value.second.name }
    }

    override suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>) {
        val sortPinnedKey = if (value.first.isNotes) sortPinnedNotesKey else sortPinnedFoldersKey
        datastore.datastore.edit { prefs -> prefs[sortPinnedKey] = value.second }
    }

    override suspend fun setGridCount(cellCount: Int) {
        datastore.datastore.edit { prefs -> prefs[gridKey] = cellCount }
    }

    override suspend fun resetSortState(key: SortDataKey) {
        val orderKey = if (key.isNotes) orderNotesKey else orderFoldersKey
        val sortKey = if (key.isNotes) sortNotesKey else sortFoldersKey
        val sortPinnedKey = if (key.isNotes) sortPinnedNotesKey else sortPinnedFoldersKey

        datastore.datastore.edit { prefs ->
            prefs[orderKey] = INITIAL_ORDER_STATE
            prefs[sortKey] = INITIAL_SORT_STATE
            prefs[sortPinnedKey] = INITIAL_IS_SORT_PINNED
        }
    }
}