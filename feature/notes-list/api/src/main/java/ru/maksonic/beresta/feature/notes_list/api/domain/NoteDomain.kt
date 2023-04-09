package ru.maksonic.beresta.feature.notes_list.api.domain

import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * @Author maksonic on 21.02.2023
 */
typealias NotesDomainList = Flow<List<NoteDomain>>
typealias NoteDomainItem = Flow<NoteDomain>

data class NoteDomain(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: Calendar = Calendar.getInstance(),
    val currentFolder: String = "",
    val isSelected: Boolean = false,
    val isPinned: Boolean = false,
    val pinTime: Calendar = Calendar.getInstance(),
    val isMovedToTrash: Boolean = false
)