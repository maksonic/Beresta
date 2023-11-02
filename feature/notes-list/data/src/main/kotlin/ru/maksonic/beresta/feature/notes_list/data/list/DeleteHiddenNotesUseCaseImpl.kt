package ru.maksonic.beresta.feature.notes_list.data.list

import ru.maksonic.beresta.feature.notes_list.domain.list.NotesRepository
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.DeleteHiddenNotesUseCase

/**
 * @Author maksonic on 23.10.2023
 */
class DeleteHiddenNotesUseCaseImpl(
    private val repository: NotesRepository
) : DeleteHiddenNotesUseCase {
    override suspend fun invoke() {
        repository.deleteHiddenNotes()
    }
}