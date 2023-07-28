package ru.maksonic.beresta.data.folders.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.data.common.BaseDataSource
import ru.maksonic.beresta.data.database.folders.FolderCache
import ru.maksonic.beresta.data.database.folders.FolderCacheItem
import ru.maksonic.beresta.data.database.folders.NoteFolderDao
import ru.maksonic.beresta.data.database.folders.FoldersCacheList

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersDataDataSource(
    private val folderDao: NoteFolderDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseDataSource<FolderCache>(folderDao, dispatcher) {

    fun fetchCacheFoldersList(): FoldersCacheList = folderDao.fetchList()
        .flowOn(dispatcher)

    fun fetchCacheFoldersTrashList(): FoldersCacheList = folderDao.fetchTrashList()
        .flowOn(dispatcher)

    fun fetchCacheOneItemById(id: Long): FolderCacheItem = folderDao.fetchItemById(id)
        .flowOn(dispatcher)
}