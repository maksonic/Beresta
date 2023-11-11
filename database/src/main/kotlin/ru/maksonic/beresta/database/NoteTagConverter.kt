package ru.maksonic.beresta.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.maksonic.beresta.database.tags.NoteTagCache

/**
 * @Author maksonic on 05.11.2023
 */
class NoteTagConverter {
    @TypeConverter
    fun tagsToJsonString(tags: List<NoteTagCache>?): String = Json.encodeToString(tags)

    @TypeConverter
    fun jsonStringToTagsList(value: String) = Json.decodeFromString<List<NoteTagCache>>(value)
}