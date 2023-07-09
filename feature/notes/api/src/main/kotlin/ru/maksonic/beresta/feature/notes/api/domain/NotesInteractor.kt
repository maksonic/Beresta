package ru.maksonic.beresta.feature.notes.api.domain

/**
 * @Author maksonic on 25.03.2023
 */
interface NotesInteractor {
    suspend fun addNote(note: NoteDomain)
    suspend fun updateNote(note: NoteDomain)
    suspend fun updateAll(notes: List<NoteDomain>)
    suspend fun deleteNote(note: NoteDomain)
    suspend fun deleteAll(notes: List<NoteDomain>)

    class Impl(private val repository: NotesRepository) : NotesInteractor {
        override suspend fun addNote(note: NoteDomain) = repository.addNewItem(note)
        override suspend fun updateNote(note: NoteDomain) = repository.updateItem(note)
        override suspend fun updateAll(notes: List<NoteDomain>) = repository.updateAllItems(notes)
        override suspend fun deleteNote(note: NoteDomain) = repository.deleteItem(note)
        override suspend fun deleteAll(notes: List<NoteDomain>) = repository.clearItemsList(notes)
    }
}