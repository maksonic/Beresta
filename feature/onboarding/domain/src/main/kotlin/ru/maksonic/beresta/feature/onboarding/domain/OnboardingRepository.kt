package ru.maksonic.beresta.feature.onboarding.domain

/**
 * @Author maksonic on 12.10.2023
 */
interface OnboardingRepository {
    fun fetchOnboardings(): OnboardingsList
}