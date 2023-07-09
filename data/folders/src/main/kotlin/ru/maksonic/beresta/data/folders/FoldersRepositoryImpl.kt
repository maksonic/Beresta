package ru.maksonic.beresta.data.folders

import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.data.folders.cache.FoldersCacheDataSource
import ru.maksonic.beresta.data.folders.cache.FolderCacheMapper
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_chips.api.domain.FolderDomainItem
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersDomainList
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersRepository


/**
 * @Author maksonic on 30.03.2023
 */
class FoldersRepositoryImpl(
    private val cache: FoldersCacheDataSource,
    private val mapper: FolderCacheMapper,
) : FoldersRepository {

    override fun fetchItemsList(): FoldersDomainList = cache.fetchCacheFoldersList()
        .transform { cacheFolders ->
            val folders = mapper.listDataToDomain(cacheFolders)
            emit(folders)
        }

    override suspend fun fetchTrashFolders(): FoldersDomainList =
        cache.fetchCacheFoldersTrashList().transform { cacheNotesList ->
            val notes = mapper.listDataToDomain(cacheNotesList)
            emit(notes)
        }

    override fun fetchItem(itemId: Long): FolderDomainItem = cache.fetchCacheOneItemById(itemId)
        .transform { cacheFolder ->
            val folder = mapper.dataToDomain(cacheFolder)
            emit(folder)
        }

    override suspend fun addNewItem(item: FolderDomain) {
        val folder = mapper.domainToData(item)
        cache.insertItem(folder)
    }

    override suspend fun updateItem(item: FolderDomain) {
        val folder = mapper.domainToData(item)
        cache.updateItem(folder)
    }

    override suspend fun deleteItem(item: FolderDomain) {
        val folder = mapper.domainToData(item)
        cache.removeItem(folder)
    }

    override suspend fun clearItemsList(items: List<FolderDomain>) {
        val folders = mapper.listDomainToData(items)
        cache.clearCache(folders)
    }

    override suspend fun updateAllItems(items: List<FolderDomain>) {
        val list = mapper.listDomainToData(items)
        cache.updateAll(list)
    }
}