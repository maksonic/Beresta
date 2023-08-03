package ru.maksonic.beresta.feature.splash_screen.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.splash_screen.api.SplashApi
import ru.maksonic.beresta.feature.splash_screen.ui.SplashScreen
import ru.maksonic.beresta.feature.splash_screen.ui.SplashViewModel

/**
 * @Author maksonic on 24.04.2023
 */
val splashScreenCoreFeatureModule = module {
    single<SplashApi.Ui> { SplashScreen() }
    viewModel { SplashViewModel(onboardingVisibility = get(), themePickerApi = get()) }
}