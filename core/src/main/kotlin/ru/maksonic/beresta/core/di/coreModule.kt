package ru.maksonic.beresta.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.core.DateFormatter

/**
 * @Author maksonic on 29.06.2023
 */
val coreModule = module {
    single<DateFormatter> { DateFormatter.Core() }
}