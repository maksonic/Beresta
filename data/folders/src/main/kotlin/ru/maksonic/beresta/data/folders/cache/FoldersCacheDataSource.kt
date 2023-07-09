package ru.maksonic.beresta.data.folders.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.data.common.BaseCacheSource
import ru.maksonic.beresta.data.database.folders.FolderCache
import ru.maksonic.beresta.data.database.folders.FolderCacheItem
import ru.maksonic.beresta.data.database.folders.NoteFolderDao
import ru.maksonic.beresta.data.database.folders.FoldersCacheList

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersCacheDataSource(
    private val folderDao: NoteFolderDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<FolderCache>(folderDao, dispatcher) {

    fun fetchCacheFoldersList(): FoldersCacheList =
        folderDao.fetchFoldersList().flowOn(dispatcher)

    fun fetchCacheFoldersTrashList(): FoldersCacheList =
        folderDao.fetchFoldersTrashList().flowOn(dispatcher)

    fun fetchCacheOneItemById(id: Long): FolderCacheItem =
        folderDao.fetchCacheOneItemById(id).flowOn(dispatcher)
}