package ru.maksonic.beresta.feature.hidden_notes_dialog.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailManager

/**
 * @Author maksonic on 05.08.2023
 */
class PinFailManagerCore(private val datastore: Datastore) : PinFailManager {

    private companion object {
        private const val BASE_KEY = "prefs_hidden_notes_pin_fail"
        private const val INITIAL_FAIL_COUNT = 0
        private const val INITIAL_IS_COOL_DOWN = false
        private const val INITIAL_END_TIME = 0L
    }

    private val failCountKey = intPreferencesKey(BASE_KEY + "count_key")
    private val coolDownKey = booleanPreferencesKey(BASE_KEY + "cool_down_key")
    private val endTimeKey = longPreferencesKey(BASE_KEY + "ent_time_key")

    override val state: Flow<PinFailInfo> = datastore.datastore.data.map { prefs ->
        PinFailInfo(
            failCount = prefs[failCountKey] ?: INITIAL_FAIL_COUNT,
            isCoolDown = prefs[coolDownKey] ?: INITIAL_IS_COOL_DOWN,
            endDate = prefs[endTimeKey] ?: INITIAL_END_TIME
        )
    }

    override suspend fun updateFailCounter(value: Int) {
        datastore.datastore.edit { prefs -> prefs[failCountKey] = value }
    }

    override suspend fun updateCoolDown(isCoolDown: Boolean, endTime: Long) {
        datastore.datastore.edit { prefs ->
            prefs[coolDownKey] = isCoolDown
            prefs[endTimeKey] = endTime
        }
    }
}