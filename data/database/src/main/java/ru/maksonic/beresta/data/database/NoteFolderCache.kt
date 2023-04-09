package ru.maksonic.beresta.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 30.03.2023
 */
typealias NotesFoldersCacheList = Flow<List<NoteFolderCache>>
typealias NoteFolderCacheItem = Flow<NoteFolderCache>

@Entity(tableName = "notes_folders")
data class NoteFolderCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = "",
    val isMovedToTrash: Boolean = false,
)