package ru.maksonic.beresta.feature.onboarding.ui.core

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.ui.api.OnboardingUiApi
import ru.maksonic.beresta.feature.onboarding.ui.core.screen.OnboardingUiCore

/**
 * @Author maksonic on 27.09.2023
 */
val onboardingUiFeatureModule = module {
    single { OnboardingProgram(repository = get(), onboardingVisibilityFeatureCase = get()) }
    viewModel { OnboardingSandbox(program = get()) }
    single<OnboardingUiApi> { OnboardingUiCore() }
}