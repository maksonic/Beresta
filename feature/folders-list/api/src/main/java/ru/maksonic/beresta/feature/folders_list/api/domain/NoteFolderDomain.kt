package ru.maksonic.beresta.feature.folders_list.api.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 30.03.2023
 */
typealias NotesFoldersDomainList = Flow<List<NoteFolderDomain>>
typealias NoteFolderDomainItem = Flow<NoteFolderDomain>

data class NoteFolderDomain(val id: Long = 0, val title: String = "", val isMovedToTrash: Boolean)