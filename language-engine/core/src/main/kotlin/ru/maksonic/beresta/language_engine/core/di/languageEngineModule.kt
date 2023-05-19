package ru.maksonic.beresta.language_engine.core.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.language_engine.core.LanguageDatastore
import ru.maksonic.beresta.language_engine.core.LanguageJsonToDataConverter
import ru.maksonic.beresta.language_engine.core.LanguageProviderImpl
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.LanguageProvider

/**
 * @Author maksonic on 22.04.2023
 */
val languageEngineModule = module {
    single<LanguageEngineApi> { LanguageDatastore(datastore = get()) }
    single<LanguageProvider> { LanguageProviderImpl(langConverter = get()) }
    single {
        LanguageJsonToDataConverter(
            gson = get(),
            jsonConverter = get(),
            dispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
}