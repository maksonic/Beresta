package ru.maksonic.beresta.data.notes_folders

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.data.notes_folders.cache.FoldersCacheDataSource
import ru.maksonic.beresta.data.notes_folders.cache.NoteFolderCacheMapper
import ru.maksonic.beresta.feature.folders_list.api.domain.NoteFolderDomain
import ru.maksonic.beresta.feature.folders_list.api.domain.NoteFolderDomainItem
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersDomainList
import ru.maksonic.beresta.feature.folders_list.api.domain.NotesFoldersRepository

/**
 * @Author maksonic on 30.03.2023
 */
class NotesFoldersRepositoryImpl(
    private val cache: FoldersCacheDataSource,
    private val mapper: NoteFolderCacheMapper,
) : NotesFoldersRepository {

    override fun fetchItemsList(): NotesFoldersDomainList = cache.fetchCachedFolders()
        .transform { cacheFolders ->
            val folders = mapper.listDataToDomain(cacheFolders)
            emit(folders)
        }

    override fun fetchItem(itemId: Long): NoteFolderDomainItem =
        cache.fetchCacheFolder(itemId).transform { folderItemCache ->
            val folder = mapper.dataToDomain(folderItemCache)
            emit(folder)
        }

    override suspend fun addNewItem(item: NoteFolderDomain) {
        val folder = mapper.domainToData(item)
        cache.addFolder(folder)
    }

    override suspend fun updateItem(item: NoteFolderDomain) {
        val folder = mapper.domainToData(item)
        cache.updateFolder(folder)
    }

    override suspend fun removeItem(item: NoteFolderDomain) {
        val folder = mapper.domainToData(item)
        cache.removeFolder(folder)
    }

    override suspend fun clearItemsList(items: List<NoteFolderDomain>) {
        val folders = mapper.listDomainToData(items)
        cache.removeAllFolders(folders)
    }
}