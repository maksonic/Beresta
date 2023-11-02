package ru.maksonic.beresta.database.folders

import androidx.room.Dao
import androidx.room.Query
import ru.maksonic.beresta.common.data.BaseDao

/**
 * @Author maksonic on 30.03.2023
 */
@Dao
abstract class FolderDao : BaseDao<FolderCache>() {

    @Query("SELECT * FROM notes_folders WHERE isMovedToTrash == 0")
    abstract fun fetchList(): FoldersCacheList

    @Query("SELECT * FROM notes_folders WHERE isMovedToTrash == 1")
    abstract fun fetchTrashList(): FoldersCacheList

    @Query("SELECT * FROM notes_folders WHERE id = :itemId")
    abstract fun fetchItemById(itemId: Long): FolderCacheItem
}