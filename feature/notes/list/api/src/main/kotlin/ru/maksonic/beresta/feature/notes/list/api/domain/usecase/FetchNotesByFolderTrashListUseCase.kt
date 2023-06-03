package ru.maksonic.beresta.feature.notes.list.api.domain.usecase

import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes.list.api.domain.NotesRepository

/**
 * @Author maksonic on 03.06.2023
 */
class FetchNotesByFolderTrashListUseCase(
    private val repository: NotesRepository,
) : BaseUseCase.Default<NotesDomainList> {
    override suspend fun invoke(): NotesDomainList = repository.fetchNotesByFolderTrashList()
}