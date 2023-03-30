package ru.maksonic.beresta.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 30.03.2023
 */
@Dao
abstract class NoteFolderDao : BaseDao<NoteFolderCache>() {

    @Transaction
    @Query("SELECT * FROM notes_folders")
    abstract fun fetchCacheItemsList(): Flow<List<NoteFolderCache>>

    @Transaction
    @Query("SELECT * FROM notes_folders WHERE id = :itemId")
    abstract fun fetchCacheOneItemById(itemId: Long): Flow<NoteFolderCache>

    @Query("SELECT EXISTS(SELECT * FROM notes_folders WHERE id = :id)")
    abstract fun isNoteIsExist(id : Long) : Boolean
}