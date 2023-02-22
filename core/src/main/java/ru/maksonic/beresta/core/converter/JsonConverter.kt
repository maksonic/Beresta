package ru.maksonic.beresta.core.converter

import java.io.IOException

/**
 * @Author maksonic on 17.02.2023
 */
interface JsonConverter {
    fun convertAssertJsonToString(fileName: String): Result<String>

    class Core(private val assetsReader: AssetsReader) : JsonConverter {

        override fun convertAssertJsonToString(fileName: String): Result<String> {
            return try {
                val readString = assetsReader.readAssetsString(fileName)
                Result.success(readString)
            } catch (e: IOException) {
                Result.failure(e)
            }
        }
    }
}
