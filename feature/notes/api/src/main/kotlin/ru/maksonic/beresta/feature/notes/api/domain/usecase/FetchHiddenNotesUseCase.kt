package ru.maksonic.beresta.feature.notes.api.domain.usecase

import ru.maksonic.beresta.core.domain.UseCase
import ru.maksonic.beresta.feature.notes.api.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository

/**
 * @Author maksonic on 28.07.2023
 */
class FetchHiddenNotesUseCase(
    private val repository: NotesRepository,
) : UseCase.Default<NotesDomainList> {
    override suspend fun invoke(): NotesDomainList = repository.fetchHiddenNotes()
}