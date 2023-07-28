package ru.maksonic.beresta.core.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 28.07.2023
 */
interface Interactor<T> {
    suspend fun fetchList(): Flow<List<T>>
    suspend fun fetchById(id: Long): Flow<T>
    suspend fun add(item: T)
    suspend fun update(item: T)
    suspend fun updateList(items: List<T>)
    suspend fun delete(item: T)
    suspend fun deleteList(items: List<T>)
}