package ru.maksonic.beresta.feature.folders_list.data

import ru.maksonic.beresta.feature.folders_list.domain.FolderDomainItem
import ru.maksonic.beresta.feature.folders_list.domain.FoldersRepository
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFolderByIdUseCase

/**
 * @Author maksonic on 17.10.2023
 */
class FetchFolderByIdUseCaseImpl(
    private val repository: FoldersRepository
) : FetchFolderByIdUseCase {
    override fun invoke(args: Long): FolderDomainItem = repository.fetchById(args)
}