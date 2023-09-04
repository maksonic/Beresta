package ru.maksonic.beresta.feature.hidden_notes_dialog.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinInfo

/**
 * @Author maksonic on 05.08.2023
 */
class PinFailCounterCore(
    private val datastore: Datastore
) : HiddenNotesApi.Feature.PinFailCounter {
    private companion object {
        private const val BASE_KEY = "prefs_hidden_notes_pin_fail"
        private const val INITIAL_FAIL_COUNT = 0
        private const val INITIAL_IS_COOL_DOWN = false
        private const val INITIAL_END_TIME = 0L
    }

    private val failCountKey = intPreferencesKey(BASE_KEY + "count_key")
    private val coolDownKey = booleanPreferencesKey(BASE_KEY + "cool_down_key")
    private val endTimeKey = longPreferencesKey(BASE_KEY + "ent_time_key")

    override val state: Flow<PinInfo> = datastore.datastore.data.map { prefs ->
        PinInfo(
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
/*class PinFailCounterCore(
    private val datastore: Datastore
) : HiddenNotesApi.Feature.PinFailCounter {
    private companion object {
        private const val BASE_KEY = "prefs_hidden_notes_pin_fail_counter"
        private const val INITIAL_FAIL_COUNT = 0
        private const val INITIAL_IS_COOL_DOWN = false
        private const val INITIAL_DATE = ""
    }

    private val counterKey = intPreferencesKey(BASE_KEY + "value_key")
    private val coolDownKey = booleanPreferencesKey(BASE_KEY + "cool_down_key")
    private val dateKey = stringPreferencesKey(BASE_KEY + "date_key")

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    override val state: Flow<PinFailInfo> = datastore.datastore.data.map { prefs ->
        PinFailInfo(
            failCount = prefs[counterKey] ?: INITIAL_FAIL_COUNT,
            isCoolDown = prefs[coolDownKey] ?: INITIAL_IS_COOL_DOWN,
            timestamp = prefs[dateKey]
        )
    }

    override suspend fun updateCounter(value: Int) {
        datastore.datastore.edit { prefs -> prefs[counterKey] = value }
    }

    override suspend fun updateCoolDown(value: Boolean) {
        datastore.datastore.edit { prefs -> prefs[coolDownKey] = value }
    }

    override suspend fun updateTimestamp(value: LocalDateTime?) {
        val formatted = if (value == null) "" else formatter.format(value)
        datastore.datastore.edit { prefs -> prefs[dateKey] = formatted }
    }

    override suspend fun reset() {
        datastore.datastore.edit { prefs ->
            prefs[counterKey] = INITIAL_FAIL_COUNT
        }
    }
}*/