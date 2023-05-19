package ru.maksonic.beresta.data.database.folders

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 30.03.2023
 */
@Dao
abstract class NoteFolderDao : BaseDao<NoteFolderCache>() {

    @Query("SELECT * FROM notes_folders WHERE isMovedToTrash == 0")
    abstract fun fetchFoldersList(): Flow<List<NoteFolderCache>>

    @Query("SELECT * FROM notes_folders WHERE isMovedToTrash == 1")
    abstract fun fetchFoldersTrashList(): Flow<List<NoteFolderCache>>

    @Query("SELECT * FROM notes_folders WHERE id = :itemId")
    abstract fun fetchCacheOneItemById(itemId: Long): Flow<NoteFolderCache>

    @Query("SELECT EXISTS(SELECT * FROM notes_folders WHERE id = :id)")
    abstract fun isFolderIsExist(id : Long) : Boolean
}