package ru.maksonic.beresta.feature.splash_screen.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.splash_screen.api.SplashUiApi
import ru.maksonic.beresta.feature.splash_screen.core.SplashScreen
import ru.maksonic.beresta.feature.splash_screen.core.SplashViewModel

/**
 * @Author maksonic on 24.04.2023
 */
val splashScreenFeatureModule = module {
    single<SplashUiApi> { SplashScreen() }
    viewModel { SplashViewModel(onboardingVisibility = get(), darkThemeChecker = get()) }
}