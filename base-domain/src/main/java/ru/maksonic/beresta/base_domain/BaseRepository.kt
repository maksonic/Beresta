package ru.maksonic.beresta.base_domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 20.12.2022
 */
interface BaseRepository<T> {
    fun fetchItemsList(): Flow<List<T>>
    fun fetchItem(itemId: Long): Flow<T>
    suspend fun addNewItem(item: T)
    suspend fun updateItem(item: T)
    suspend fun updateAllItems(items: List<T>)
    suspend fun removeEmptyItem(item: T)
    suspend fun removeItem(item: T)
    suspend fun clearItemsList(items: List<T>)
}