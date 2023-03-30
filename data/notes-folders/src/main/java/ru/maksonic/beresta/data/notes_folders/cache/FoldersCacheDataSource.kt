package ru.maksonic.beresta.data.notes_folders.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.data.common.BaseCacheSource
import ru.maksonic.beresta.data.database.NoteFolderCache
import ru.maksonic.beresta.data.database.NoteFolderCacheItem
import ru.maksonic.beresta.data.database.NoteFolderDao
import ru.maksonic.beresta.data.database.NotesFoldersCacheList

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersCacheDataSource(
    private val folderDao: NoteFolderDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<NoteFolderCache>(folderDao, dispatcher) {
    fun fetchCachedFolders(): NotesFoldersCacheList = folderDao.fetchCacheItemsList().flowOn(dispatcher)
    fun fetchCacheFolder(id: Long): NoteFolderCacheItem =
        folderDao.fetchCacheItemById(id).flowOn(dispatcher)

    suspend fun addFolder(folder: NoteFolderCache) = folderDao.insertItem(folder)
    suspend fun updateFolder(folder: NoteFolderCache) = folderDao.updateItem(folder)
    suspend fun removeFolder(folder: NoteFolderCache) = folderDao.deleteItem(folder)
    suspend fun removeAllFolders(folders: List<NoteFolderCache>) = folderDao.deleteAll(folders)
}