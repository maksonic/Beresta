package ru.maksonic.beresta.core

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @Author maksonic on 15.12.2022
 */
enum class CoroutineDispatchers {
    IO, MAIN, DEFAULT
}

val coreModule = module {
    single(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
    single(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
    single(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
}