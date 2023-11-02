package ru.maksonic.beresta.feature.onboarding.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 12.10.2023
 */
interface OnboardingVisibilityFeatureCase {
    val visibilityState: Flow<Boolean>
    suspend fun notShowOnboardingAgain()
}