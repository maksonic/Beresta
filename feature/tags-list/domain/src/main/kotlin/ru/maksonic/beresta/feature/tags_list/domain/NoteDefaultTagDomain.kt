package ru.maksonic.beresta.feature.tags_list.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 05.11.2023
 */
typealias TagsList = Flow<List<NoteDefaultTagDomain>>
typealias TagItem = Flow<NoteDefaultTagDomain>

data class NoteDefaultTagDomain(val id: Long, val title: String, val isDefault: Boolean)