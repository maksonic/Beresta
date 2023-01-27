package ru.maksonic.beresta.feature.notes_list.domain

import ru.maksonic.beresta.base_domain.BaseUseCase

/**
 * @Author maksonic on 27.01.2023
 */
class FetchNoteByIdUseCase(
    private val repository: NotesRepository
) : BaseUseCase.WithArgs<NoteDomainItem, Long> {
    override suspend fun invoke(args: Long): NoteDomainItem = repository.fetchItem(args)
}