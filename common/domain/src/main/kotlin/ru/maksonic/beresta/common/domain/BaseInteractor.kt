package ru.maksonic.beresta.common.domain

/**
 * @Author maksonic on 13.10.2023
 */
abstract class BaseInteractor<T>(private val repository: Repository<T>) : Interactor<T> {
    override suspend fun add(item: T) = repository.add(item)
    override suspend fun update(item: T) = repository.update(item)
    override suspend fun updateList(items: List<T>) = repository.updateList(items)
    override suspend fun delete(item: T) = repository.delete(item)
    override suspend fun deleteList(items: List<T>) = repository.deleteList(items)
}