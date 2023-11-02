package ru.maksonic.beresta.feature.notes_list.data.list

import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesRepository
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesUseCase

/**
 * @Author maksonic on 15.10.2023
 */
class FetchNotesUseCaseImpl(private val repository: NotesRepository) : FetchNotesUseCase {
    override fun invoke(): NotesDomainList = repository.fetchList()
}