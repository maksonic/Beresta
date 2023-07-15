package ru.maksonic.beresta.feature.folders_chips.api.domain

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @Author maksonic on 30.03.2023
 */
typealias FoldersDomainList = Flow<List<FolderDomain>>
typealias FolderDomainItem = Flow<FolderDomain>

data class FolderDomain(
    val id: Long = 0,
    val title: String = "",
    val isSelected: Boolean = false,
    val isMovedToTrash: Boolean = false,
    val isPinned: Boolean = false,
    val isSticky: Boolean = false,
    val pinTime: LocalDateTime? = null,
    val dateCreation: LocalDateTime = LocalDateTime.now(),
    val dateLastUpdateRaw: LocalDateTime? = null,
    val isSelectable: Boolean = true,
    val isStickyToStart: Boolean = false,
    val isStickyToEnd: Boolean = false,
    val dateMovedToTrash: LocalDateTime? = null,
)