package ru.maksonic.beresta.data.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * @Author maksonic on 19.12.2022
 */
abstract class BaseCacheSource<T>(
    private val baseDao: BaseDao<T>,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend fun insertAll(item: T) = withContext(dispatcher) { baseDao.insertAll(listOf(item)) }
    suspend fun insertItem(item: T): Long = withContext(dispatcher) { baseDao.insertItem(item) }
    suspend fun updateItem(item: T) = withContext(dispatcher) { baseDao.updateItem(item) }
    suspend fun removeItem(item: T) = withContext(dispatcher) { baseDao.deleteItem(item) }
    suspend fun clearCache(items: List<T>) = withContext(dispatcher) { baseDao.deleteAll(items)}
}