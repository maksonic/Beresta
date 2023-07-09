package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 20.05.2023
 */
interface ArgumentReceiver {
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
    fun getLongArray(key: String): LongArray?
    fun getListLongFromString(key: String): List<Long>
    fun getString(key: String): String
}