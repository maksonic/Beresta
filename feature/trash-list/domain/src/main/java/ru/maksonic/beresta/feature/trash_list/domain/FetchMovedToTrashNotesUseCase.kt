package ru.maksonic.beresta.feature.trash_list.domain

import ru.maksonic.beresta.base_domain.BaseUseCase
import ru.maksonic.beresta.feature.notes_list.domain.NotesDomainList
import ru.maksonic.beresta.feature.notes_list.domain.NotesRepository

/**
 * @Author maksonic on 24.01.2023
 */
class FetchMovedToTrashNotesUseCase(
    private val repository: NotesRepository
) : BaseUseCase.Default<NotesDomainList> {
    override suspend fun invoke(): NotesDomainList = repository.fetchNotesMovedToTrash()
}