package ru.maksonic.beresta.data.notes_folders

import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.data.notes_folders.cache.FoldersCacheDataSource
import ru.maksonic.beresta.data.notes_folders.cache.NoteFolderCacheMapper
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomain
import ru.maksonic.beresta.feature.notes.folders.api.domain.NoteFolderDomainItem
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersDomainList
import ru.maksonic.beresta.feature.notes.folders.api.domain.NotesFoldersRepository

/**
 * @Author maksonic on 30.03.2023
 */
class NotesFoldersRepositoryImpl(
    private val cache: FoldersCacheDataSource,
    private val mapper: NoteFolderCacheMapper,
) : NotesFoldersRepository {

    override fun fetchItemsList(): NotesFoldersDomainList = cache.fetchCacheFoldersList()
        .transform { cacheFolders ->
            val folders = mapper.listDataToDomain(cacheFolders)
            emit(folders)
        }

    override fun fetchItem(itemId: Long): NoteFolderDomainItem = cache.fetchCacheOneItemById(itemId)
        .transform { cacheFolder ->
            val folder = mapper.dataToDomain(cacheFolder)
            emit(folder)
        }

    override suspend fun addNewItem(item: NoteFolderDomain) {
        val folder = mapper.domainToData(item)
        cache.insertItem(folder)
    }

    override suspend fun updateItem(item: NoteFolderDomain) {
        val folder = mapper.domainToData(item)
        cache.updateItem(folder)
    }

    override suspend fun removeItem(item: NoteFolderDomain) {
        val folder = mapper.domainToData(item)
        cache.removeItem(folder)
    }

    override suspend fun clearItemsList(items: List<NoteFolderDomain>) {
        val folders = mapper.listDomainToData(items)
        cache.clearCache(folders)
    }

    override suspend fun updateAllItems(items: List<NoteFolderDomain>) {
        val list = mapper.listDomainToData(items)
        cache.updateAll(list)
    }
}