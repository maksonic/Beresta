package ru.maksonic.beresta.common.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * @Author maksonic on 19.12.2022
 */
abstract class BaseDataSource<T>(
    private val baseDao: BaseDao<T>,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun insertAll(items: List<T>) = withContext(ioDispatcher) { baseDao.insertAll(items) }
    suspend fun insertItem(item: T): Long = withContext(ioDispatcher) { baseDao.insertItem(item) }
    suspend fun updateItem(item: T) = withContext(ioDispatcher) { baseDao.updateItem(item) }
    suspend fun updateAll(items: List<T>) = withContext(ioDispatcher) { baseDao.updateAll(items) }
    suspend fun removeItem(item: T) = withContext(ioDispatcher) { baseDao.deleteItem(item) }
    suspend fun clearCache(items: List<T>) = withContext(ioDispatcher) { baseDao.deleteAll(items) }
}