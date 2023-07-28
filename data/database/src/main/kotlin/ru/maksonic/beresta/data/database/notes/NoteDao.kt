package ru.maksonic.beresta.data.database.notes

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 21.02.2023
 */
@Dao
abstract class NoteDao : BaseDao<NoteCache>() {

    @Query("SELECT * FROM notes WHERE isMovedToTrash == 0 AND isHidden == 0")
    abstract fun fetchList(): Flow<List<NoteCache>>

    @Query("SELECT * FROM notes WHERE isMovedToTrash == 1 AND folderId != 2 AND isHidden == 0")
    abstract fun fetchTrashListByFolder(): Flow<List<NoteCache>>

    @Query("SELECT * FROM notes WHERE isMovedToTrash == 1 AND folderId == 2 AND isHidden == 0")
    abstract fun fetchWithoutFolderTrashList(): Flow<List<NoteCache>>

    @Query("SELECT * FROM notes WHERE id = :itemId")
    abstract fun fetchItemById(itemId: Long): Flow<NoteCache>

    @Query("SELECT * FROM notes WHERE isHidden == 1")
    abstract fun fetchHiddenList(): Flow<List<NoteCache>>
}