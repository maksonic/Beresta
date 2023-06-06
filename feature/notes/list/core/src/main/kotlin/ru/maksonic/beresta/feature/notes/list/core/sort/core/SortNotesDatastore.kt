package ru.maksonic.beresta.feature.notes.list.core.sort.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesSortPrefs
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes

/**
 * @Author maksonic on 05.06.2023
 */
class SortNotesDatastore(private val datastore: Datastore) : NotesListApi.SortedNotesState {

    private companion object {
        private const val IS_SORT_PINNED_NOTES = false
        private const val INITIAL_NOTES_GRID_COUNT = 1
    }

    private val sortKey = stringPreferencesKey("prefs_app_sort_notes_key")
    private val sortPinnedKey = booleanPreferencesKey("prefs_app_sort_pinned_notes_key")
    private val gridCountKey = intPreferencesKey("prefs_app_grid_count_notes_key")

    override val current: Flow<NotesSortPrefs> = datastore.datastore.data.map { pref ->
        val sort = SortedNotes.valueOf(pref[sortKey] ?: SortedNotes.DATE_CREATION_DESC.name)
        val isPinned = pref[sortPinnedKey] ?: IS_SORT_PINNED_NOTES
        val gridCount = pref[gridCountKey] ?: INITIAL_NOTES_GRID_COUNT
        Triple(sort, isPinned, gridCount)
    }

    override suspend fun setSortBy(sort: SortedNotes) {
        datastore.datastore.edit { preferences -> preferences[sortKey] = sort.name }
    }

    override suspend fun setSortPinned(isSorted: Boolean) {
        datastore.datastore.edit { preferences -> preferences[sortPinnedKey] = isSorted }
    }

    override suspend fun setGridView(count: Int) {
        datastore.datastore.edit { preferences -> preferences[gridCountKey] = count }
    }
}