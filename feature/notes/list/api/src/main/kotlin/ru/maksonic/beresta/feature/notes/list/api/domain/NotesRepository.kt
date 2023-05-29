package ru.maksonic.beresta.feature.notes.list.api.domain

import ru.maksonic.beresta.core.domain.BaseRepository

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesRepository : BaseRepository<NoteDomain> {
    suspend fun fetchTrashNotes(): NotesDomainList
    suspend fun removeEmptyItem(item: NoteDomain)
}