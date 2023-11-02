package ru.maksonic.beresta.feature.folders_list.data

import ru.maksonic.beresta.feature.folders_list.domain.FoldersDomainList
import ru.maksonic.beresta.feature.folders_list.domain.FoldersRepository
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersUseCase

/**
 * @Author maksonic on 17.10.2023
 */
class FetchFoldersUseCaseImpl(private val repository: FoldersRepository) : FetchFoldersUseCase {
    override fun invoke(): FoldersDomainList = repository.fetchList()
}