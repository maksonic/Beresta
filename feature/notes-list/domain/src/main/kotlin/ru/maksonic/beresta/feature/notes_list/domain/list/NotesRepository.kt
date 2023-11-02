package ru.maksonic.beresta.feature.notes_list.domain.list

import ru.maksonic.beresta.common.domain.Repository
import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList

/**
 * @Author maksonic on 13.10.2023
 */
interface NotesRepository : Repository<NoteDomain> {
    fun fetchNotesWithoutFolderTrashList(): NotesDomainList
    fun fetchNotesByFolderTrashList(): NotesDomainList
    fun fetchHiddenNotes(): NotesDomainList
    suspend fun deleteHiddenNotes()
}