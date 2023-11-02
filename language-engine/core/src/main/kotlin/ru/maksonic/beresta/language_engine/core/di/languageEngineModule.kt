package ru.maksonic.beresta.language_engine.core.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.language_engine.core.LanguageJsonToDataConverter
import ru.maksonic.beresta.language_engine.core.LanguageProviderCore
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider

/**
 * @Author maksonic on 22.04.2023
 */
val languageEngineModule = module {
    single<LanguageProvider> { LanguageProviderCore(langConverter = get()) }
    single {
        LanguageJsonToDataConverter(
            jsonConverter = get(),
            json = get(),
            dispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
}