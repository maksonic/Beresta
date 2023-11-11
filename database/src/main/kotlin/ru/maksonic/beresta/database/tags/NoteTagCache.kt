package ru.maksonic.beresta.database.tags

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
/**
 * @Author maksonic on 05.11.2023
 */
typealias NoteTagsCacheList = Flow<List<NoteTagCache>>
typealias NoteTagCacheItem = Flow<NoteTagCache>

@Entity(tableName = "note_tags")
@Serializable
data class NoteTagCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String
)