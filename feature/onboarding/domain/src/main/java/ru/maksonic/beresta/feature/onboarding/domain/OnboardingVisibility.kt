package ru.maksonic.beresta.feature.onboarding.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 15.12.2022
 */
interface OnboardingVisibility {
    suspend fun notShowAgain()
    val currentState: Flow<Boolean>
}