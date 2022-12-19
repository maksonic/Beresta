package ru.maksonic.beresta.feature.onboarding.data

import org.koin.dsl.module
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.feature.onboarding.domain.Repository

/**
 * @Author maksonic on 19.12.2022
 */
val onboardingDataModule = module {
    single<OnboardingVisibility> { OnboardingVisibilityDatastore(datastore = get()) }
    single<Repository> { OnboardingRepository }
}