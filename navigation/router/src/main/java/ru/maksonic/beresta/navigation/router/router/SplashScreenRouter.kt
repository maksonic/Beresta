package ru.maksonic.beresta.navigation.router.router

/**
 * @Author maksonic on 24.01.2023
 */
data class SplashScreenRouter(val toOnboardingOrMain: (destination: String?) -> Unit)