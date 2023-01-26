package ru.maksonic.beresta.navigation.router

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.router.*

/**
 * @Author maksonic on 15.11.2022
 */
abstract class BaseNavigator : AbstractNavigator() {
    abstract fun splashRouter(entry: NavBackStackEntry): SplashScreenRouter
    abstract fun onboardingRouter(entry: NavBackStackEntry): OnboardingRouter
    abstract fun mainRouter(entry: NavBackStackEntry): MainScreenRouter
    abstract fun settingsRouter(entry: NavBackStackEntry): SettingsScreenRouter
    abstract fun trashRouter(entry: NavBackStackEntry): TrashScreenRouter
    abstract fun editNoteRouter(entry: NavBackStackEntry): EditNoteRouter
}
