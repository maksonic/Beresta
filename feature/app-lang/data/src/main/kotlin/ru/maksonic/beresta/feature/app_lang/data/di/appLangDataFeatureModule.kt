package ru.maksonic.beresta.feature.app_lang.data.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.app_lang.data.AppLangRepositoryImpl
import ru.maksonic.beresta.feature.app_lang.data.ChangeAppLangUseCaseImpl
import ru.maksonic.beresta.feature.app_lang.data.DateFormatterCore
import ru.maksonic.beresta.feature.app_lang.data.FetchAppLangUseCaseImpl
import ru.maksonic.beresta.feature.app_lang.data.local.LanguageDataStore
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.formatter.DateFormatter
import ru.maksonic.beresta.feature.app_lang.domain.usecase.ChangeAppLangUseCase
import ru.maksonic.beresta.feature.app_lang.domain.usecase.FetchAppLangUseCase

/**
 * @Author maksonic on 15.10.2023
 */
val appLangDataFeatureModule = module {
    single { LanguageDataStore(datastore = get()) }
    single<AppLangRepository> { AppLangRepositoryImpl(languageDataStore = get()) }
    factory<FetchAppLangUseCase> { FetchAppLangUseCaseImpl(repository = get()) }
    factory<ChangeAppLangUseCase> { ChangeAppLangUseCaseImpl(repository = get()) }
    factory<DateFormatter> { DateFormatterCore() }
}