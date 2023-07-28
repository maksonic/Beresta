package ru.maksonic.beresta.data.database.folders

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 30.03.2023
 */
@Dao
abstract class NoteFolderDao : BaseDao<FolderCache>() {

    @Query("SELECT * FROM notes_folders WHERE isMovedToTrash == 0")
    abstract fun fetchList(): Flow<List<FolderCache>>

    @Query("SELECT * FROM notes_folders WHERE isMovedToTrash == 1")
    abstract fun fetchTrashList(): Flow<List<FolderCache>>

    @Query("SELECT * FROM notes_folders WHERE id = :itemId")
    abstract fun fetchItemById(itemId: Long): Flow<FolderCache>
}