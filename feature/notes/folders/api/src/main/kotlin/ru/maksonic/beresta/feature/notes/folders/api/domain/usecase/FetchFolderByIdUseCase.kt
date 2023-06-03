package ru.maksonic.beresta.feature.notes.folders.api.domain.usecase

import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomainItem
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersRepository

/**
 * @Author maksonic on 09.04.2023
 */
class FetchFolderByIdUseCase(
    private val repository: NotesFoldersRepository
) : BaseUseCase.WithArgs<NoteFolderDomainItem, Long> {
    override suspend fun invoke(args: Long): NoteFolderDomainItem = repository.fetchItem(args)
}