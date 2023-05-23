package ru.maksonic.beresta.feature.notes.list.api.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @Author maksonic on 21.02.2023
 */
typealias NotesDomainList = Flow<List<NoteDomain>>
typealias NoteDomainItem = Flow<NoteDomain>

data class NoteDomain(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: LocalDateTime = LocalDateTime.now(),
    val isSelected: Boolean = false,
    val isPinned: Boolean = false,
    val pinTime: LocalDateTime? = null,
    val isMovedToTrash: Boolean = false,
    val folderId: Long = 2L
)