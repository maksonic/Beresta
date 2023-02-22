package ru.maksonic.beresta.core.converter

import android.content.Context

/**
 * @Author maksonic on 17.02.2023
 */
interface AssetsReader {
    fun readAssetsString(fileName: String): String

    class Core(private val context: Context) : AssetsReader {
        override fun readAssetsString(fileName: String): String = context.assets
            .open(fileName)
            .bufferedReader()
            .use { bufferReader -> bufferReader.readText() }
    }
}
