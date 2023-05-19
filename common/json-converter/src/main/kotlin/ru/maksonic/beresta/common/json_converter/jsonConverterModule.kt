package ru.maksonic.beresta.common.json_converter

import com.google.gson.Gson
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @Author maksonic on 22.04.2023
 */
val jsonConverterModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single { Gson() }
    single<AssetsReader> { AssetsReader.Core(androidContext()) }
    single<JsonConverter> { JsonConverter.Core(assetsReader = get()) }
}