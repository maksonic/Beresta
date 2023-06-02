package ru.maksonic.beresta.navigation.router

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.router.*
import ru.maksonic.beresta.navigation.router.router.trash.TrashFoldersScreenRouter
import ru.maksonic.beresta.navigation.router.router.trash.TrashNotesScreenRouter

/**
 * @Author maksonic on 15.11.2022
 */
interface Router {
    fun splashRouter(entry: NavBackStackEntry): SplashScreenRouter
    fun onboardingRouter(entry: NavBackStackEntry): OnboardingRouter
    fun mainRouter(entry: NavBackStackEntry): MainScreenRouter
    fun editNoteRouter(entry: NavBackStackEntry): EditNoteRouter
    fun settingsRouter(entry: NavBackStackEntry): SettingsScreenRouter
    fun trashNotesRouter(entry: NavBackStackEntry): TrashNotesScreenRouter
    fun trashFoldersRouter(entry: NavBackStackEntry): TrashFoldersScreenRouter
    fun foldersRouter(entry: NavBackStackEntry): NotesFoldersScreenRouter
}
