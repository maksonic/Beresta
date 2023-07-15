package ru.maksonic.beresta.navigation.router

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.navigation.router.router.FoldersScreenRouter
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.navigation.router.router.settings.SettingsScreenRouter
import ru.maksonic.beresta.navigation.router.router.SplashScreenRouter
import ru.maksonic.beresta.navigation.router.router.settings.SettingsAppearanceScreenRouter
import ru.maksonic.beresta.navigation.router.router.trash.TrashFoldersScreenRouter
import ru.maksonic.beresta.navigation.router.router.trash.TrashNotesScreenRouter

/**
 * @Author maksonic on 15.11.2022
 */
interface Router {
    fun splashRouter(entry: NavBackStackEntry): SplashScreenRouter
    fun onboardingRouter(entry: NavBackStackEntry): OnboardingRouter
    fun mainRouter(entry: NavBackStackEntry): MainScreenRouter
    fun settingsRouter(entry: NavBackStackEntry): SettingsScreenRouter
    fun settingsAppearanceRouter(entry: NavBackStackEntry): SettingsAppearanceScreenRouter
    fun editNoteRouter(entry: NavBackStackEntry): EditNoteRouter
    fun foldersRouter(entry: NavBackStackEntry): FoldersScreenRouter
    fun trashNotesRouter(entry: NavBackStackEntry): TrashNotesScreenRouter
    fun trashFoldersRouter(entry: NavBackStackEntry): TrashFoldersScreenRouter
}
