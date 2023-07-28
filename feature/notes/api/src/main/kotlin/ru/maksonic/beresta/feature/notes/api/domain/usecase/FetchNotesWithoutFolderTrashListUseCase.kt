package ru.maksonic.beresta.feature.notes.api.domain.usecase

import ru.maksonic.beresta.core.domain.UseCase
import ru.maksonic.beresta.feature.notes.api.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository

/**
 * @Author maksonic on 30.05.2023
 */
class FetchNotesWithoutFolderTrashListUseCase(
    private val repository: NotesRepository,
) : UseCase.Default<NotesDomainList> {
    override suspend fun invoke(): NotesDomainList = repository.fetchNotesWithoutFolderTrashList()
}