package ru.maksonic.beresta.feature.onboarding.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.ui.core.Program

/**
 * @Author maksonic on 19.12.2022
 */
val onboardingFeatureModule = module {
    single {
        Program(repository = get(), onboardingVisibility = get(), navigator = get())
    }
    viewModel { OnboardingSandbox(program = get()) }
}