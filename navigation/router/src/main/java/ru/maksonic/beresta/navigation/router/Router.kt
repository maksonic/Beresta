package ru.maksonic.beresta.navigation.router

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.router.*

/**
 * @Author maksonic on 15.11.2022
 */
interface Router {
    fun splashRouter(entry: NavBackStackEntry): SplashScreenRouter
    fun onboardingRouter(entry: NavBackStackEntry): OnboardingRouter
    fun mainRouter(entry: NavBackStackEntry): MainScreenRouter
    fun settingsRouter(entry: NavBackStackEntry): SettingsScreenRouter
    fun trashRouter(entry: NavBackStackEntry): TrashScreenRouter
    fun editNoteRouter(entry: NavBackStackEntry): EditNoteRouter
}
