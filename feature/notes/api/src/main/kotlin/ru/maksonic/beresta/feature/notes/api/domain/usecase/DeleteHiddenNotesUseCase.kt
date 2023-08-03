package ru.maksonic.beresta.feature.notes.api.domain.usecase

import ru.maksonic.beresta.core.domain.UseCase
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository

/**
 * @Author maksonic on 01.08.2023
 */
class DeleteHiddenNotesUseCase(
    private val repository: NotesRepository,
) : UseCase.Default<Unit> {
    override suspend fun invoke(): Unit = repository.deleteHiddenNotes()
}