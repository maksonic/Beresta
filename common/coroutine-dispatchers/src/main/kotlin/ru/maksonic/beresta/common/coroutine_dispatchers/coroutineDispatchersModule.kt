package ru.maksonic.beresta.common.coroutine_dispatchers

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @Author maksonic on 22.04.2023
 */
val coroutineDispatchersModule = module {
    single(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
    single(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
    single(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
}