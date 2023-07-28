package ru.maksonic.beresta.feature.notes.api.domain

import ru.maksonic.beresta.core.domain.Repository

/**
 * @Author maksonic on 21.02.2023
 */
interface NotesRepository : Repository<NoteDomain> {
    suspend fun fetchNotesWithoutFolderTrashList(): NotesDomainList
    suspend fun fetchNotesByFolderTrashList(): NotesDomainList
    suspend fun fetchHiddenNotes(): NotesDomainList
}