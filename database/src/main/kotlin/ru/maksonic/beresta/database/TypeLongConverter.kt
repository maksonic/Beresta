package ru.maksonic.beresta.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * @Author maksonic on 11.11.2023
 */
class TypeLongConverter {
    @TypeConverter
    fun listLongToJsonString(list: List<Long>?): String = Json.encodeToString(list)

    @TypeConverter
    fun jsonStringToListLong(value: String) = Json.decodeFromString<List<Long>>(value)
}