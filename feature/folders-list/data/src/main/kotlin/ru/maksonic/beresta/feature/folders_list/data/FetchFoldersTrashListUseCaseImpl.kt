package ru.maksonic.beresta.feature.folders_list.data

import ru.maksonic.beresta.feature.folders_list.domain.FoldersDomainList
import ru.maksonic.beresta.feature.folders_list.domain.FoldersRepository
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersTrashListUseCase

/**
 * @Author maksonic on 17.10.2023
 */
class FetchFoldersTrashListUseCaseImpl(
    private val repository: FoldersRepository
) : FetchFoldersTrashListUseCase {
    override fun invoke(): FoldersDomainList = repository.fetchFoldersTrashList()
}