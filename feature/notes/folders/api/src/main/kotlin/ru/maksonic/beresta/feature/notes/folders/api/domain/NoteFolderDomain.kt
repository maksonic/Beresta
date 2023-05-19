package ru.maksonic.beresta.feature.notes.folders.api.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @Author maksonic on 30.03.2023
 */
typealias NotesFoldersDomainList = Flow<List<NoteFolderDomain>>
typealias NoteFolderDomainItem = Flow<NoteFolderDomain>

data class NoteFolderDomain(
    val id: Long = 0,
    val title: String = "",
    val isMovedToTrash: Boolean = false,
    val isPinned: Boolean = false,
    val isSticky: Boolean = false,
    val pinTime: LocalDateTime? = null,
    val dateCreation: LocalDateTime = LocalDateTime.now(),
)