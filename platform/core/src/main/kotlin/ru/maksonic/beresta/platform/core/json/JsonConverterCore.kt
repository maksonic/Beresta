package ru.maksonic.beresta.platform.core.json

import ru.maksonic.beresta.common.core.json.AssetsReader
import ru.maksonic.beresta.common.core.json.JsonConverter

/**
 * @Author maksonic on 14.10.2023
 */
class JsonConverterCore(private val assetsReader: AssetsReader) : JsonConverter {
    override fun convertFileToString(fileName: String): Result<String> = runCatching {
        assetsReader.readAssetsString(fileName)
    }
}