package ru.maksonic.beresta.feature.onboarding.data.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.data.OnboardingRepositoryCore
import ru.maksonic.beresta.feature.onboarding.data.OnboardingVisibilityFeatureCaseImpl
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingRepository
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibilityFeatureCase

/**
 * @Author maksonic on 27.09.2023
 */
val onboardingDataFeatureModule = module {
    single<OnboardingVisibilityFeatureCase> {
        OnboardingVisibilityFeatureCaseImpl(datastore = get())
    }
    single<OnboardingRepository> {
        OnboardingRepositoryCore(json = get(), jsonConverter = get(), fetchAppLangUseCase = get())
    }
}