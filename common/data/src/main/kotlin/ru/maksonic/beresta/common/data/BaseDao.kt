package ru.maksonic.beresta.common.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * @Author maksonic on 19.12.2022
 */
@Dao
abstract class BaseDao<T> {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(data: List<T>)

    @Transaction
    @Insert
    abstract suspend fun insertItem(item: T): Long

    open fun fetchCacheList(): Flow<List<T>> = emptyFlow()

    @Transaction
    @Update
    abstract suspend fun updateItem(item: T)

    @Transaction
    @Update
    abstract suspend fun updateAll(items: List<T>)

    @Transaction
    @Delete
    abstract suspend fun deleteAll(data: List<T>)

    @Delete
    abstract suspend fun deleteItem(item: T)
}