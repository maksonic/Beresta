package ru.maksonic.beresta.core.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 21.02.2023
 */
interface Repository<T> {
    fun fetchList(): Flow<List<T>>
    fun fetchById(itemId: Long): Flow<T>
    suspend fun add(item: T): Long
    suspend fun update(item: T)
    suspend fun updateList(items: List<T>)
    suspend fun delete(item: T)
    suspend fun deleteList(items: List<T>)
}