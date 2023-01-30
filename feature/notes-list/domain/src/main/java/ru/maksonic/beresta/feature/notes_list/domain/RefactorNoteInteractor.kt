package ru.maksonic.beresta.feature.notes_list.domain

/**
 * @Author maksonic on 27.01.2023
 */
interface RefactorNoteInteractor {
    suspend fun addNote(note: NoteDomain)
    suspend fun updateNote(note: NoteDomain)
    suspend fun removeNote(note: NoteDomain)
    suspend fun removeAll(notes: List<NoteDomain>)
    suspend fun updateAll(notes: List<NoteDomain>)

    class Impl(private val repository: NotesRepository) : RefactorNoteInteractor {
        override suspend fun addNote(note: NoteDomain) = repository.addNewItem(note)
        override suspend fun updateNote(note: NoteDomain) = repository.updateItem(note)
        override suspend fun removeNote(note: NoteDomain) = repository.removeItem(note)
        override suspend fun removeAll(notes: List<NoteDomain>) = repository.clearItemsList(notes)
        override suspend fun updateAll(notes: List<NoteDomain>) = repository.updateAllItems(notes)
    }
}