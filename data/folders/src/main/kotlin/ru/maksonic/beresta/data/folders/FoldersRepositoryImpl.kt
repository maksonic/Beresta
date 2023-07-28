package ru.maksonic.beresta.data.folders

import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.data.common.BaseRepository
import ru.maksonic.beresta.data.database.folders.FolderCache
import ru.maksonic.beresta.data.folders.cache.FoldersDataDataSource
import ru.maksonic.beresta.data.folders.cache.FolderCacheMapper
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomainItem
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersDomainList
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersRepository


/**
 * @Author maksonic on 30.03.2023
 */
class FoldersRepositoryImpl(
    private val cache: FoldersDataDataSource,
    private val mapper: FolderCacheMapper,
) : BaseRepository<FolderCache, FolderDomain>(cache, mapper), FoldersRepository {

    override fun fetchList(): FoldersDomainList =
        cache.fetchCacheFoldersList().transform { emit(mapper.listDataToDomain(it)) }

    override suspend fun fetchTrashFolders(): FoldersDomainList =
        cache.fetchCacheFoldersTrashList().transform { emit(mapper.listDataToDomain(it)) }

    override fun fetchById(itemId: Long): FolderDomainItem =
        cache.fetchCacheOneItemById(itemId).transform { emit(mapper.dataToDomain(it)) }
}