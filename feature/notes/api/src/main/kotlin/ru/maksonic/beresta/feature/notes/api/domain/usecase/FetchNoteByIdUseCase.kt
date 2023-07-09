package ru.maksonic.beresta.feature.notes.api.domain.usecase

import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.notes.api.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes.api.domain.NotesRepository

/**
 * @Author maksonic on 21.02.2023
 */
class FetchNoteByIdUseCase(
    private val repository: NotesRepository
) : BaseUseCase.WithArgs<NoteDomainItem, Long> {
    override suspend fun invoke(args: Long): NoteDomainItem = repository.fetchItem(args)
}