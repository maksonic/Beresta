package ru.maksonic.beresta.feature.sorting_sheet.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.core.ui.sorting.Order
import ru.maksonic.beresta.common.core.ui.sorting.Sort
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.sorting_sheet.domain.GridCountKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.FoldersSortDomain
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.NotesSortDomain

/**
 * @Author maksonic on 16.10.2023
 */
class SortDataStore(private val datastore: Datastore) {
    private companion object {
        private val INITIAL_ORDER_STATE = Order.DESCENDING.name
        private val INITIAL_SORT_STATE = Sort.DATE_CREATION.name
        private const val INITIAL_IS_SORT_PINNED = false
        private const val INITIAL_NOTES_GRID_COUNT = 1
        private const val GRID_KEY = "prefs_app_key_grid_count"
        private const val ORDER_KEY = "prefs_app_key_order"
        private const val SORT_KEY = "prefs_app_key_sort"
        private const val PINNED_KEY = "prefs_app_key_sort_pinned"
        private const val NOTES = "_notes"
        private const val HIDDEN_NOTES = "_hidden_notes"
        private const val FOLDERS = "_folders"
    }

    private val gridNotesKey = intPreferencesKey(GRID_KEY + NOTES)
    private val gridHiddenNotesKey = intPreferencesKey(GRID_KEY + HIDDEN_NOTES)

    private val orderNotesKey = stringPreferencesKey(ORDER_KEY + NOTES)
    private val sortNotesKey = stringPreferencesKey(SORT_KEY + NOTES)
    private val sortPinnedNotesKey = booleanPreferencesKey(PINNED_KEY + NOTES)

    private val orderHiddenNotesKey = stringPreferencesKey(ORDER_KEY + HIDDEN_NOTES)
    private val sortHiddenNotesKey = stringPreferencesKey(SORT_KEY + HIDDEN_NOTES)
    private val sortPinnedHiddenNotesKey = booleanPreferencesKey(PINNED_KEY + HIDDEN_NOTES)

    private val orderFoldersKey = stringPreferencesKey(ORDER_KEY + FOLDERS)
    private val sortFoldersKey = stringPreferencesKey(SORT_KEY + FOLDERS)
    private val sortPinnedFoldersKey = booleanPreferencesKey(PINNED_KEY + FOLDERS)

    private fun orderKey(key: SortDataKey) = when (key) {
        SortDataKey.NOTES -> orderNotesKey
        SortDataKey.HIDDEN_NOTES -> orderHiddenNotesKey
        SortDataKey.FOLDERS -> orderFoldersKey
    }

    private fun sortKey(key: SortDataKey) = when (key) {
        SortDataKey.NOTES -> sortNotesKey
        SortDataKey.HIDDEN_NOTES -> sortHiddenNotesKey
        SortDataKey.FOLDERS -> sortFoldersKey
    }

    private fun pinnedKey(key: SortDataKey) = when (key) {
        SortDataKey.NOTES -> sortPinnedNotesKey
        SortDataKey.HIDDEN_NOTES -> sortPinnedHiddenNotesKey
        SortDataKey.FOLDERS -> sortPinnedFoldersKey
    }

    fun fetchNotesSort(): Flow<NotesSortDomain> = datastore.datastore.data.map { prefs ->
        NotesSortDomain(
            order = Order.valueOf(prefs[orderNotesKey] ?: INITIAL_ORDER_STATE),
            sort = Sort.valueOf(prefs[sortNotesKey] ?: INITIAL_SORT_STATE),
            isSortPinned = prefs[sortPinnedNotesKey] ?: INITIAL_IS_SORT_PINNED,
            gridCount = prefs[gridNotesKey] ?: INITIAL_NOTES_GRID_COUNT
        )
    }

    fun fetchHiddenNotesSort(): Flow<NotesSortDomain> = datastore.datastore.data.map { prefs ->
        NotesSortDomain(
            order = Order.valueOf(prefs[orderHiddenNotesKey] ?: INITIAL_ORDER_STATE),
            sort = Sort.valueOf(prefs[sortHiddenNotesKey] ?: INITIAL_SORT_STATE),
            isSortPinned = prefs[sortPinnedHiddenNotesKey] ?: INITIAL_IS_SORT_PINNED,
            gridCount = prefs[gridHiddenNotesKey] ?: INITIAL_NOTES_GRID_COUNT
        )
    }

    fun fetchFoldersSort(): Flow<FoldersSortDomain> = datastore.datastore.data.map { prefs ->
        FoldersSortDomain(
            order = Order.valueOf(prefs[orderFoldersKey] ?: INITIAL_ORDER_STATE),
            sort = Sort.valueOf(prefs[sortFoldersKey] ?: INITIAL_SORT_STATE),
            isSortPinned = prefs[sortPinnedFoldersKey] ?: INITIAL_IS_SORT_PINNED,
        )
    }

    suspend fun setOrderState(value: Pair<SortDataKey, Order>) {
        datastore.datastore.edit { prefs -> prefs[orderKey(value.first)] = value.second.name }
    }

    suspend fun setSortState(value: Pair<SortDataKey, Sort>) {
        datastore.datastore.edit { prefs -> prefs[sortKey(value.first)] = value.second.name }
    }

    suspend fun setCheckboxState(value: Pair<SortDataKey, Boolean>) {
        datastore.datastore.edit { prefs -> prefs[pinnedKey(value.first)] = value.second }
    }

    suspend fun setGridCount(value: Pair<GridCountKey, Int>) {
        val key = if (value.first == GridCountKey.NOTES) gridNotesKey else gridHiddenNotesKey
        datastore.datastore.edit { prefs -> prefs[key] = value.second }
    }

    suspend fun resetSortState(key: SortDataKey) {
        datastore.datastore.edit { prefs ->
            prefs[orderKey(key)] = INITIAL_ORDER_STATE
            prefs[sortKey(key)] = INITIAL_SORT_STATE
            prefs[pinnedKey(key)] = INITIAL_IS_SORT_PINNED
        }
    }
}