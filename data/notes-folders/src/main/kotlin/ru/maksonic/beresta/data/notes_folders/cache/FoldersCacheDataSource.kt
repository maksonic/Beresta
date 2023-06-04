package ru.maksonic.beresta.data.notes_folders.cache

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.beresta.data.common.BaseCacheSource
import ru.maksonic.beresta.data.database.folders.NoteFolderCache
import ru.maksonic.beresta.data.database.folders.NoteFolderCacheItem
import ru.maksonic.beresta.data.database.folders.NoteFolderDao
import ru.maksonic.beresta.data.database.folders.NotesFoldersCacheList

/**
 * @Author maksonic on 30.03.2023
 */
class FoldersCacheDataSource(
    private val folderDao: NoteFolderDao,
    private val dispatcher: CoroutineDispatcher,
) : BaseCacheSource<NoteFolderCache>(folderDao, dispatcher) {

    fun fetchCacheFoldersList(): NotesFoldersCacheList =
        folderDao.fetchFoldersList().flowOn(dispatcher)

    fun fetchCacheFoldersTrashList(): NotesFoldersCacheList =
        folderDao.fetchFoldersTrashList().flowOn(dispatcher)

    fun fetchCacheOneItemById(id: Long): NoteFolderCacheItem =
        folderDao.fetchCacheOneItemById(id).flowOn(dispatcher)
}