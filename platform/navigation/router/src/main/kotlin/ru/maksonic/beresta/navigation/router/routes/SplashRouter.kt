package ru.maksonic.beresta.navigation.router.routes

/**
 * @Author maksonic on 24.01.2023
 */
data class SplashRouter(val toOnboardingOrMain: (destination: String?) -> Unit)