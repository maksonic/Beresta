package ru.maksonic.beresta.feature.onboarding.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.OnboardingProgram
import ru.maksonic.beresta.feature.onboarding.core.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.OnboardingScreen
import ru.maksonic.beresta.feature.onboarding.core.OnboardingVisibilityStore

/**
 * @Author maksonic on 24.04.2023
 */
val onboardingProgramFeatureModule = module {
    single<OnboardingApi.VisibilityBehavior> { OnboardingVisibilityStore(datastore = get()) }
    single { OnboardingProgram(onboardingVisibility = get()) }
    viewModel { OnboardingSandbox(program = get()) }
    single<OnboardingApi.Ui> { OnboardingScreen() }
}