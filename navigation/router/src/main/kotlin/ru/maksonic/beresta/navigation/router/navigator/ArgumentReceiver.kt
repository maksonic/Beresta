package ru.maksonic.beresta.navigation.router.navigator

/**
 * @Author maksonic on 20.05.2023
 */
interface ArgumentReceiver {
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
    fun getLongArray(key: String): LongArray?
    fun getString(key: String): String
}