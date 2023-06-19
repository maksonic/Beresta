package ru.maksonic.beresta.feature.notes.list.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteCardCornerRadius
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes

/**
 * @Author maksonic on 17.06.2023
 */
class NoteCardStateDatastore(private val datastore: Datastore) : NotesListApi.CardState {

    private val radiusKey = stringPreferencesKey("prefs_app_note_card_corner_radius_key")
    private val mutableCardState = MutableStateFlow(NoteCardUiState.Initial)

    override val current: Flow<NoteCardUiState> = datastore.datastore.data.map { pref ->
        val radius =
            NoteCardCornerRadius.valueOf(pref[radiusKey] ?: NoteCardCornerRadius.ROUNDED.name)
        mutableCardState.update { it.copy(cornerRadius = radius) }
        mutableCardState.value
    }

    override suspend fun updateCornerRadius(radius: NoteCardCornerRadius) {
        datastore.datastore.edit { preferences -> preferences[radiusKey] = radius.name }
        mutableCardState.updateAndGet { it.copy(cornerRadius = radius) }
    }
    /* override val current: Flow<NotesSortPrefs> = datastore.datastore.data.map { pref ->
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
     }*/
}