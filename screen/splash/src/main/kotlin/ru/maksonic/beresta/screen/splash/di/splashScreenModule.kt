package ru.maksonic.beresta.screen.splash.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.splash.SplashViewModel

/**
 * @Author maksonic on 24.04.2023
 */
val splashScreenModule = module {
    viewModel { SplashViewModel(onboardingVisibilityFeatureCase = get()) }
}