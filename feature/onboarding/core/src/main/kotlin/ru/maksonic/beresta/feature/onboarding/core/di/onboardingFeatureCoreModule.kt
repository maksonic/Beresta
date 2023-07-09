package ru.maksonic.beresta.feature.onboarding.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.OnboardingFeatureCore

/**
 * @Author maksonic on 19.06.2023
 */
val onboardingCoreFeatureModule = module {
    single<OnboardingApi.Feature> { OnboardingFeatureCore(datastore = get()) }
}