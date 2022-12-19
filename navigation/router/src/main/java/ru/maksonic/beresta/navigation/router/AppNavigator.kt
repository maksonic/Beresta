package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
class AppNavigator: AbstractNavigator(), Router {
    override fun onboardingToMain() {
        navigate(Destination.Main.route)
    }

    override fun mainToSettings() {
        navigate(Destination.Settings.route)
    }
}