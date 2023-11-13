package ru.maksonic.beresta.database.tags

import androidx.room.Dao
import androidx.room.Query
import ru.maksonic.beresta.common.data.BaseDao

/**
 * @Author maksonic on 05.11.2023
 */
@Dao
abstract class NoteTagDao : BaseDao<NoteTagCache>() {
    @Query("SELECT * FROM note_tags WHERE id != 25")
    abstract fun fetchList(): NoteTagsCacheList

    @Query("SELECT * FROM note_tags WHERE id = :itemId")
    abstract fun fetchItemById(itemId: Long): NoteTagCacheItem
}