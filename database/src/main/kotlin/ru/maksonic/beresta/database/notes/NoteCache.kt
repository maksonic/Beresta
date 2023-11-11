package ru.maksonic.beresta.database.notes

import androidx.room.ColumnInfo
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
    val wallpaperTypeId: Int = 0,
    val wallpaperId: Long = 0L,
    val wallpaperTintId: Long = 0L,
    val wallpaperBackgroundColorId: Long = 0L,
    val isTextureStyle: Boolean = false,
    val isDarkWallpaper: Boolean = false,
    @ColumnInfo(name = "tags") val tagsIds: List<Long> = emptyList()
)