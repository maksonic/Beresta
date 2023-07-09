package ru.maksonic.beresta.feature.folders_chips.api.domain.usecase

import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomainItem
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersRepository

/**
 * @Author maksonic on 09.04.2023
 */
class FetchFolderByIdUseCase(
    private val repository: FoldersRepository
) : BaseUseCase.WithArgs<FolderDomainItem, Long> {
    override suspend fun invoke(args: Long): FolderDomainItem = repository.fetchItem(args)
}