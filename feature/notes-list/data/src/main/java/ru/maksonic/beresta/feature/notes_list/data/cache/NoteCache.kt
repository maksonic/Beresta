package ru.maksonic.beresta.feature.notes_list.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

/**
 * @Author maksonic on 19.12.2022
 */
typealias NotesCacheList = Flow<List<NoteCache>>
typealias NoteCacheItem = Flow<NoteCache>

@Entity(tableName = "notes")
data class NoteCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: Calendar = Calendar.getInstance(),
    val currentFolder: String = "",
    val isPinned: Boolean = false,
    val isMovedToTrash: Boolean = false
)