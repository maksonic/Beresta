package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
interface Router {
    fun splashToOnboarding()
    fun splashToMain()
    fun onboardingToMain()
    fun mainToSettings()
}