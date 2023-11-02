package ru.maksonic.beresta.platform.core.json

import android.content.Context
import ru.maksonic.beresta.common.core.json.AssetsReader

/**
 * @Author maksonic on 14.10.2023
 */
class AssetsReaderCore(private val context: Context) : AssetsReader {
    override fun readAssetsString(fileName: String): String = context.assets
        .open(fileName)
        .bufferedReader()
        .use { bufferReader -> bufferReader.readText() }
}