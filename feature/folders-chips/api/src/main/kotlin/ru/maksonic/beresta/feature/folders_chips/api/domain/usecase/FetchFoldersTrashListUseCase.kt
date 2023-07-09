package ru.maksonic.beresta.feature.folders_chips.api.domain.usecase

import ru.maksonic.beresta.core.domain.BaseUseCase
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersDomainList
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersRepository

/**
 * @Author maksonic on 03.06.2023
 */
class FetchFoldersTrashListUseCase(
    private val repository: FoldersRepository,
) : BaseUseCase.Default<FoldersDomainList> {
    override suspend fun invoke(): FoldersDomainList = repository.fetchTrashFolders()
}