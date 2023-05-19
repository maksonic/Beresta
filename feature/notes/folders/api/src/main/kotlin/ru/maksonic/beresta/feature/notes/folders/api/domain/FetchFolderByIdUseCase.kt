package ru.maksonic.beresta.feature.notes.folders.api.domain

import ru.maksonic.beresta.core.domain.BaseUseCase

/**
 * @Author maksonic on 09.04.2023
 */
class FetchFolderByIdUseCase(
    private val repository: NotesFoldersRepository
) : BaseUseCase.WithArgs<NoteFolderDomainItem, Long> {
    override suspend fun invoke(args: Long): NoteFolderDomainItem = repository.fetchItem(args)
}