package ru.maksonic.beresta.feature.notes_list.domain

import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * @Author maksonic on 19.12.2022
 */
typealias NotesDomainList = Flow<List<NoteDomain>>
typealias NoteDomainItem = Flow<NoteDomain>

data class NoteDomain(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: Calendar = Calendar.getInstance(),
    val isMovedToTrash: Boolean = false
)