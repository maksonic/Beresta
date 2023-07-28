package ru.maksonic.beresta.feature.folders_chips.api.domain

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.core.domain.Interactor

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersInteractor(private val repository: FoldersRepository) : Interactor<FolderDomain> {
    override suspend fun fetchList(): Flow<List<FolderDomain>> = repository.fetchList()
    override suspend fun fetchById(id: Long): Flow<FolderDomain> = repository.fetchById(id)
    override suspend fun add(item: FolderDomain) = repository.add(item)
    override suspend fun update(item: FolderDomain) = repository.update(item)
    override suspend fun updateList(items: List<FolderDomain>) = repository.updateList(items)
    override suspend fun delete(item: FolderDomain) = repository.delete(item)
    override suspend fun deleteList(items: List<FolderDomain>) = repository.deleteList(items)
}