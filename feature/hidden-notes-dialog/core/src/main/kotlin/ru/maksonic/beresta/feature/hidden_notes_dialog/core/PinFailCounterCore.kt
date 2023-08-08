package ru.maksonic.beresta.feature.hidden_notes_dialog.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailInfo
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

/**
 * @Author maksonic on 05.08.2023
 */
/*class PinFailCounterCore(private val datastore: DataStore<PinFailInfo>) : HiddenNotesApi.Feature.PinFailCounter {
    override val state: Flow<PinFailInfo> = datastore.data

    override suspend fun updateCounter(value: Int) {
        datastore.updateData { it.copy(failCount = value) }
    }

    override suspend fun updateDelay(value: Long) {
        datastore.updateData { it.copy(currentDelay = value) }
    }

    override suspend fun reset() {
        datastore.updateData { PinFailInfo.INITIAL }

    }
}

object PinFailInfoSerializer  : Serializer<PinFailInfo> {

    override val defaultValue = PinFailInfo.INITIAL

    override suspend fun readFrom(input: InputStream): PinFailInfo =
        try {
            Json.decodeFromString(
                PinFailInfo.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read Settings", serialization)
        }


    override suspend fun writeTo(t: PinFailInfo, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(PinFailInfo.serializer(), t)
                    .encodeToByteArray()
            )
        }
    }
}*/


val DEFAULT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.of(1970, 1, 1, 1, 0))

class PinFailCounterCore(
    private val datastore: Datastore
) : HiddenNotesApi.Feature.PinFailCounter {
    private companion object {
        private const val BASE_KEY = "prefs_hidden_notes_pin_fail_counter"
        private const val INITIAL_FAIL_COUNT = 0
        private const val INITIAL_IS_COOL_DOWN = false
        private const val INITIAL_TICK = 15
        private const val INITIAL_DATE = ""
    }

    private val counterKey = intPreferencesKey(BASE_KEY + "value_key")
    private val coolDownKey = booleanPreferencesKey(BASE_KEY + "cool_down_key")
    private val tickKey = intPreferencesKey(BASE_KEY + "tick_key")
    private val dateKey = stringPreferencesKey(BASE_KEY + "date_key")

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    override val state: Flow<PinFailInfo> = datastore.datastore.data.map { prefs ->
        val countValue = prefs[counterKey] ?: INITIAL_FAIL_COUNT
        val coolDownValue = prefs[coolDownKey] ?: INITIAL_IS_COOL_DOWN
        val tickValue = prefs[tickKey] ?: INITIAL_TICK
        val startTimerDate = prefs[dateKey] ?: DEFAULT_DATE
        val timestamp = LocalDateTime.parse(startTimerDate, formatter)

        PinFailInfo(
            failCount = countValue,
            isCoolDown = coolDownValue,
            tick = tickValue,
            timestamp = timestamp
        )
    }

    override suspend fun updateCounter(value: Int) {
        datastore.datastore.edit { prefs -> prefs[counterKey] = value }
    }

    override suspend fun updateCoolDown(value: Boolean) {
        datastore.datastore.edit { prefs -> prefs[coolDownKey] = value }
    }

    override suspend fun updateTick(value: Int) {
        datastore.datastore.edit { prefs -> prefs[tickKey] = value }
    }

    override suspend fun updateTimestamp(value: LocalDateTime?) {
        val formatted = if (value == null) "" else formatter.format(value)
        datastore.datastore.edit { prefs -> prefs[dateKey] = formatted }
    }

    override suspend fun reset() {
        datastore.datastore.edit { prefs ->
            prefs[counterKey] = INITIAL_FAIL_COUNT
            prefs[tickKey] = INITIAL_TICK
        }
    }
}