package ru.maksonic.beresta.feature.onboarding.domain

/**
 * @Author maksonic on 19.12.2022
 */
interface Repository {
    val onboardings: Array<OnboardingEntity>
}