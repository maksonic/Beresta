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
    val folderId: Long = 2L,
    val title: String = "",
    val message: String = "",
    val dateCreation: LocalDateTime = LocalDateTime.now(),
    val dateLastUpdateRaw: LocalDateTime? = null,
    val pinTime: LocalDateTime? = null,
    val dateMovedToTrash: LocalDateTime? = null,
    val isPinned: Boolean = false,
    val isHidden: Boolean = false,
    val isMovedToTrash: Boolean = false,
    val markerColorId: Long = 0L,
    val wallpaperId: Long = 0L
)