package ru.maksonic.beresta.feature.app_theme.data.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.app_theme.data.AppThemeRepositoryImpl
import ru.maksonic.beresta.feature.app_theme.data.local.ThemeDataStore
import ru.maksonic.beresta.feature.app_theme.data.local.ThemePaletteDataStore
import ru.maksonic.beresta.feature.app_theme.domain.AppThemeRepository
import ru.maksonic.beresta.feature.app_theme.domain.usecase.AppThemeInteractor
import ru.maksonic.beresta.feature.app_theme.domain.usecase.FetchAppThemeContainerUseCase
import ru.maksonic.beresta.feature.app_theme.domain.usecase.FetchAppThemeDarkModeUseCase

/**
 * @Author maksonic on 12.10.2023
 */
val appThemeDataFeatureModule = module {
    single { ThemeDataStore(datastore = get()) }
    single { ThemePaletteDataStore(datastore = get()) }
    single<AppThemeRepository> {
        AppThemeRepositoryImpl(
            themeDataStore = get(),
            paletteDataStore = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    single<AppThemeInteractor> { AppThemeInteractor.Core(repository = get()) }
    single { FetchAppThemeContainerUseCase(repository = get()) }
    single { FetchAppThemeDarkModeUseCase(repository = get()) }
}