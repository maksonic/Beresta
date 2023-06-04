package ru.maksonic.beresta.data.database.folders

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

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
    val isPinned: Boolean = false,
    val isSticky: Boolean = false,
    val pinTime: LocalDateTime? = null,
    val dateCreation: LocalDateTime = LocalDateTime.now(),
    val isSelectable: Boolean = true,
    val isStickyToStart: Boolean = false,
    val isStickyToEnd: Boolean = false,
    val dateMovedToTrash: LocalDateTime? = null
)