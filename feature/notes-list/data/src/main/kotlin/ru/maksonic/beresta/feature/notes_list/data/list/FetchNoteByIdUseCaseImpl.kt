package ru.maksonic.beresta.feature.notes_list.data.list

import ru.maksonic.beresta.feature.notes_list.domain.NoteDomainItem
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesRepository
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNoteByIdUseCase

/**
 * @Author maksonic on 15.10.2023
 */
class FetchNoteByIdUseCaseImpl(private val repository: NotesRepository) : FetchNoteByIdUseCase {
    override fun invoke(args: Long): NoteDomainItem = repository.fetchById(args)
}