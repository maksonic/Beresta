package ru.maksonic.beresta.feature.notes_list.domain

import ru.maksonic.beresta.base_domain.BaseUseCase

/**
 * @Author maksonic on 20.12.2022
 */
class FetchNotesUseCase(
    private val repository: NotesRepository,
) : BaseUseCase.Default<NotesDomainList> {
    override suspend fun invoke(): NotesDomainList = repository.fetchItemsList()
}