package ru.maksonic.beresta.feature.notes_list.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @Author maksonic on 13.10.2023
 */
typealias NotesDomainList = Flow<List<NoteDomain>>
typealias NoteDomainItem = Flow<NoteDomain>

data class NoteDomain(
    val id: Long = 0L,
    val folderId: Long = 2L,
    val title: String = "",
    val message: String = "",
    val dateCreationRaw: LocalDateTime = LocalDateTime.now(),
    val dateCreation: String = "",
    val dateLastUpdateRaw: LocalDateTime? = null,
    val pinTime: LocalDateTime? = null,
    val dateMovedToTrashRaw: LocalDateTime? = null,
    val dateMovedToTrash: String? = "",
    val isHidden: Boolean = false,
    val isPinned: Boolean = false,
    val isMovedToTrash: Boolean = false,
    val markerColorId: Long = 0L,
    val wallpaperTypeId: Int = 0,
    val wallpaperId: Long = 0L,
    val wallpaperTintId: Long = 0L,
    val wallpaperBackgroundColorId: Long = 0L,
    val isTextureStyle: Boolean = false,
    val isDarkWallpaper: Boolean = false
)