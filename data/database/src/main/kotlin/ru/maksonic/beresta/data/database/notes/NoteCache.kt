package ru.maksonic.beresta.data.database.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
typealias NotesCacheList = Flow<List<NoteCache>>
typealias NoteCacheItem = Flow<NoteCache>

@Entity(tableName = "notes")
data class NoteCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: LocalDateTime = LocalDateTime.now(),
    val currentFolder: String = "",
    val isPinned: Boolean = false,
    val pinTime: LocalDateTime? = null,
    val isMovedToTrash: Boolean = false
)