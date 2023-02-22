package ru.maksonic.beresta.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 21.02.2023
 */
@Dao
abstract class NoteDao : BaseDao<NoteCache>() {

    @Transaction
    @Query("SELECT * FROM notes")
    abstract fun fetchCacheItemsList(): Flow<List<NoteCache>>

    @Transaction
    @Query("SELECT * FROM notes WHERE id = :itemId")
    abstract fun fetchCacheOneItemById(itemId: Long): Flow<NoteCache>

    @Query("SELECT EXISTS(SELECT * FROM notes WHERE id = :id)")
    abstract fun isNoteIsExist(id : Long) : Boolean
}