package ru.maksonic.beresta.data.common

import ru.maksonic.beresta.core.domain.Repository

/**
 * @Author maksonic on 27.07.2023
 */
abstract class BaseRepository<D, T>(
    private val cache: BaseDataSource<D>,
    private val mapper: DataMapper<D, T>
) : Repository<T> {

    override suspend fun add(item: T) {
        val cacheItem = mapper.domainToData(item)
        cache.insertItem(cacheItem)
    }

    override suspend fun update(item: T) {
        val cacheItem = mapper.domainToData(item)
        cache.updateItem(cacheItem)
    }

    override suspend fun delete(item: T) {
        val cacheItem = mapper.domainToData(item)
        cache.removeItem(cacheItem)
    }

    override suspend fun deleteList(items: List<T>) {
        val cacheList = mapper.listDomainToData(items)
        cache.clearCache(cacheList)
    }

    override suspend fun updateList(items: List<T>) {
        val cacheList = mapper.listDomainToData(items)
        cache.updateAll(cacheList)
    }
}