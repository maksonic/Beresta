package ru.maksonic.beresta.data.common

import androidx.room.*
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