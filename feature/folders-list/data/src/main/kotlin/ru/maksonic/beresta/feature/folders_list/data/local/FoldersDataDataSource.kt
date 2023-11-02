package ru.maksonic.beresta.feature.folders_list.data.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.common.data.BaseDataSource
import ru.maksonic.beresta.database.folders.FolderCache
import ru.maksonic.beresta.database.folders.FolderCacheItem
import ru.maksonic.beresta.database.folders.FolderDao
import ru.maksonic.beresta.database.folders.FoldersCacheList

/**
 * @Author maksonic on 21.02.2023
 */
class FoldersDataDataSource(
    private val folderDao: FolderDao,
    private val ioDispatcher: CoroutineDispatcher,
) : BaseDataSource<FolderCache>(folderDao, ioDispatcher) {

    fun fetchCacheFoldersList(): FoldersCacheList = folderDao.fetchList().flowOn(ioDispatcher)

    fun fetchCacheFoldersTrashList(): FoldersCacheList = folderDao.fetchTrashList()
        .flowOn(ioDispatcher)

    fun fetchCacheOneItemById(id: Long): FolderCacheItem = folderDao.fetchItemById(id)
        .flowOn(ioDispatcher)
}