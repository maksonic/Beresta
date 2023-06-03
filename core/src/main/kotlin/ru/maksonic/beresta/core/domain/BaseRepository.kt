package ru.maksonic.beresta.core.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 21.02.2023
 */
interface BaseRepository<T> {
    fun fetchItemsList(): Flow<List<T>>
    fun fetchItem(itemId: Long): Flow<T>
    suspend fun addNewItem(item: T)
    suspend fun updateItem(item: T)
    suspend fun deleteItem(item: T)
    suspend fun clearItemsList(items: List<T>)
    suspend fun updateAllItems(items: List<T>)
}