package ru.maksonic.beresta.feature.hidden_notes_dialog.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi

/**
 * @Author maksonic on 15.07.2023
 */
class HiddenNotesFeatureCore(private val datastore: Datastore) : HiddenNotesApi.Feature {
    private val key = stringPreferencesKey("prefs_hidden_notes_pwd_key")

    override val pinCode: Flow<String> = datastore.datastore.data.map { prefs -> prefs[key] ?: "" }

    override suspend fun updatePassword(new: String) {
        datastore.datastore.edit { prefs -> prefs[key] = new }
    }
}