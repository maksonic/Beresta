package ru.maksonic.beresta.common.domain

/**
 * @Author maksonic on 13.10.2023
 */
interface Interactor<T> {
    suspend fun add(item: T): Long
    suspend fun update(item: T)
    suspend fun updateList(items: List<T>)
    suspend fun delete(item: T)
    suspend fun deleteList(items: List<T>)
}