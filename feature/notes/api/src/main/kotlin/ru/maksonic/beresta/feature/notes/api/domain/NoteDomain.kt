package ru.maksonic.beresta.feature.notes.api.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
typealias NotesDomainList = Flow<List<NoteDomain>>
typealias NoteDomainItem = Flow<NoteDomain>

data class NoteDomain(
    val id: Long = 0L,
    val folderId: Long = 2L,
    val title: String = "",
    val message: String = "",
    val dateCreationRaw: LocalDateTime = LocalDateTime.now(),
    val dateLastUpdateRaw: LocalDateTime? = null,
    val pinTime: LocalDateTime? = null,
    val dateMovedToTrash: LocalDateTime? = null,
    val isHidden: Boolean = false,
    val isPinned: Boolean = false,
    val isMovedToTrash: Boolean = false,
    val markerColorId: Long = 0L,
    val wallpaperId: Long = 0L,
)