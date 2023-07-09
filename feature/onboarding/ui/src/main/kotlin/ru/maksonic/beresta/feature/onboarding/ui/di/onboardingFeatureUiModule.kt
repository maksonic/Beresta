package ru.maksonic.beresta.feature.onboarding.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.ui.OnboardingScreen
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingProgram
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingSandbox

/**
 * @Author maksonic on 19.06.2023
 */
val onboardingUiFeatureModule = module {
    single { OnboardingProgram(onboardingApi = get()) }
    single<OnboardingApi.Ui> { OnboardingScreen() }
    viewModel { OnboardingSandbox(program = get()) }
}