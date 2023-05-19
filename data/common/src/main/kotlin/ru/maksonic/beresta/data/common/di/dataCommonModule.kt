package ru.maksonic.beresta.data.common.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.maksonic.beresta.data.common.Datastore

/**
 * @Author maksonic on 24.04.2023
 */
val dataCommonModule = module {
    single<Datastore> { Datastore.Core(androidContext()) }
}