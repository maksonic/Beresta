package ru.maksonic.beresta.feature.notes_list.data.list

import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesRepository
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchHiddenNotesUseCase

/**
 * @Author maksonic on 21.10.2023
 */
class FetchHiddenNotesUseCaseImpl(
    private val repository: NotesRepository
): FetchHiddenNotesUseCase {
    override fun invoke(): NotesDomainList = repository.fetchHiddenNotes()
}