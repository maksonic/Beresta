package ru.maksonic.beresta.common.core.json

/**
 * @Author maksonic on 14.10.2023
 */
interface AssetsReader {
    fun readAssetsString(fileName: String): String
}