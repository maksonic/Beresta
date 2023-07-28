package ru.maksonic.beresta.feature.notes.api.domain.usecase

import ru.maksonic.beresta.core.domain.UseCase
import ru.maksonic.beresta.feature.notes.api.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository

/**
 * @Author maksonic on 03.06.2023
 */
class FetchNotesByFolderTrashListUseCase(
    private val repository: NotesRepository,
) : UseCase.Default<NotesDomainList> {
    override suspend fun invoke(): NotesDomainList = repository.fetchNotesByFolderTrashList()
}