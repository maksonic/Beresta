package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
class AppNavigator: AbstractNavigator(), Router {

    override fun splashToOnboarding() {
        navigate(Destination.Onboarding.route)
    }

    override fun splashToMain() {
        navigate(Destination.Main.route)
    }
    override fun onboardingToMain() {
        navigate(Destination.Main.route)
    }

    override fun mainToSettings() {
        navigate(Destination.Settings.route)
    }
}