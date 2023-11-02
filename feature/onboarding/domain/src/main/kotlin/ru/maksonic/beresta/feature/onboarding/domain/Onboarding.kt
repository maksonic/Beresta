package ru.maksonic.beresta.feature.onboarding.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 12.10.2023
 */
typealias OnboardingsList = Flow<Result<List<Onboarding>>>

data class Onboarding(
    val id: Long,
    val title: String,
    val description: String,
    val image: Int
)