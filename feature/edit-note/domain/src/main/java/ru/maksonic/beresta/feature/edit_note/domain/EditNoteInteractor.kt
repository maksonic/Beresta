package ru.maksonic.beresta.feature.edit_note.domain

import ru.maksonic.beresta.feature.notes_list.domain.NoteDomain
import ru.maksonic.beresta.feature.notes_list.domain.NotesRepository

/**
 * @Author maksonic on 27.01.2023
 */
interface EditNoteInteractor {
    suspend fun addNote(note: NoteDomain)
    suspend fun updateNote(note: NoteDomain)
    suspend fun removeNote(note: NoteDomain)

    class Impl(private val repository: NotesRepository) : EditNoteInteractor {

        override suspend fun addNote(note: NoteDomain) = repository.addNewItem(note)
        override suspend fun updateNote(note: NoteDomain) = repository.updateItem(note)
        override suspend fun removeNote(note: NoteDomain) = repository.removeItem(note)
    }
}