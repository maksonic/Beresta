package ru.maksonic.beresta.common.data

import ru.maksonic.beresta.common.core.Mapper
import ru.maksonic.beresta.common.domain.Repository

/**
 * @Author maksonic on 27.07.2023
 */
abstract class BaseRepository<D, T>(
    private val cache: BaseDataSource<D>,
    private val mapper: Mapper<D, T>
) : Repository<T> {

    override suspend fun add(item: T): Long {
        val cacheItem = mapper.mapFrom(item)
        return cache.insertItem(cacheItem)
    }

    override suspend fun update(item: T) {
        val cacheItem = mapper.mapFrom(item)
        cache.updateItem(cacheItem)
    }

    override suspend fun delete(item: T) {
        val cacheItem = mapper.mapFrom(item)
        cache.removeItem(cacheItem)
    }

    override suspend fun deleteList(items: List<T>) {
        val cacheList = mapper.mapListFrom(items)
        cache.clearCache(cacheList)
    }

    override suspend fun updateList(items: List<T>) {
        val cacheList = mapper.mapListFrom(items)
        cache.updateAll(cacheList)
    }
}