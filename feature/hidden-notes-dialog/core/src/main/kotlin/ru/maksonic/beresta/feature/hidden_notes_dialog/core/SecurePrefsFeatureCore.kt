package ru.maksonic.beresta.feature.hidden_notes_dialog.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinVisibilityUiState

/**
 * @Author maksonic on 03.08.2023
 */
class SecurePrefsFeatureCore(private val datastore: Datastore) : HiddenNotesApi.Feature.SecurePrefs {
    private val pinKey = booleanPreferencesKey("prefs_hidden_notes_pin_visibility_key")
    private val tapKey = booleanPreferencesKey("prefs_hidden_notes_pin_visibility_tap_key")

    override val state: Flow<PinVisibilityUiState> =
        datastore.datastore.data.map { prefs ->
            val pinVisibility = prefs[pinKey] ?: false
            val tapKey = prefs[tapKey] ?: true

            PinVisibilityUiState(pinVisibility, tapKey)
        }

    override suspend fun updatePinVisibility(isVisible: Boolean) {
        datastore.datastore.edit { prefs -> prefs[pinKey] = isVisible }    }

    override suspend fun updateKeyTapVisibility(isVisible: Boolean) {
        datastore.datastore.edit { prefs -> prefs[tapKey] = isVisible }    }
}