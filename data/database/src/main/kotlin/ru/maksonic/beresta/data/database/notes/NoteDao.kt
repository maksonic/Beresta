package ru.maksonic.beresta.data.database.notes

import androidx.room.Dao
import androidx.room.Query
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 21.02.2023
 */
@Dao
abstract class NoteDao : BaseDao<NoteCache>() {

    @Query("SELECT * FROM notes WHERE isMovedToTrash == 0 AND isHidden == 0")
    abstract fun fetchList(): NotesCacheList

    @Query("SELECT * FROM notes WHERE isMovedToTrash == 1 AND folderId != 2 AND isHidden == 0")
    abstract fun fetchTrashListByFolder(): NotesCacheList

    @Query("SELECT * FROM notes WHERE isMovedToTrash == 1 AND folderId == 2")
    abstract fun fetchWithoutFolderTrashList(): NotesCacheList

    @Query("SELECT * FROM notes WHERE id = :itemId")
    abstract fun fetchItemById(itemId: Long): NoteCacheItem

    @Query("SELECT * FROM notes WHERE isHidden == 1 AND isMovedToTrash == 0")
    abstract fun fetchHiddenList(): NotesCacheList
}