package ru.maksonic.beresta.feature.notes_list.data.cache

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.data.common.BaseDao

/**
 * @Author maksonic on 19.12.2022
 */
@Dao
abstract class NoteDao : BaseDao<NoteCache>() {

    @Transaction
    @Query("SELECT * FROM notes")
    abstract fun fetchCacheItemsList(): Flow<List<NoteCache>>

    @Transaction
    @Query("SELECT * FROM notes WHERE id = :itemId")
    abstract fun fetchCacheOneItemById(itemId: Long): Flow<NoteCache>
}