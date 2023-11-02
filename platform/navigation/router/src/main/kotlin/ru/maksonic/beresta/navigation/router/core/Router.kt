package ru.maksonic.beresta.navigation.router.core

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.routes.EditNoteRouter
import ru.maksonic.beresta.navigation.router.routes.FoldersRouter
import ru.maksonic.beresta.navigation.router.routes.HiddenNotesRouter
import ru.maksonic.beresta.navigation.router.routes.MainRouter
import ru.maksonic.beresta.navigation.router.routes.OnboardingRouter
import ru.maksonic.beresta.navigation.router.routes.SplashRouter
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsAppearanceScreenRouter
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsNotificationsScreenRouter
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsScreenRouter
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsSecurityScreenRouter
import ru.maksonic.beresta.navigation.router.routes.trash.TrashFoldersRouter
import ru.maksonic.beresta.navigation.router.routes.trash.TrashNotesRouter

/**
 * @Author maksonic on 15.11.2022
 */
interface Router {
    fun splashRouter(entry: NavBackStackEntry): SplashRouter
    fun onboardingRouter(entry: NavBackStackEntry): OnboardingRouter
    fun mainRouter(entry: NavBackStackEntry): MainRouter
    fun editNoteRouter(entry: NavBackStackEntry): EditNoteRouter
    fun foldersRouter(entry: NavBackStackEntry): FoldersRouter
    fun hiddenNotesRouter(entry: NavBackStackEntry): HiddenNotesRouter
    fun settingsRouter(entry: NavBackStackEntry): SettingsScreenRouter
    fun settingsAppearanceRouter(entry: NavBackStackEntry): SettingsAppearanceScreenRouter
    fun settingsNotificationsRouter(entry: NavBackStackEntry): SettingsNotificationsScreenRouter
    fun settingsSecurityRouter(entry: NavBackStackEntry): SettingsSecurityScreenRouter
    fun trashNotesRouter(entry: NavBackStackEntry): TrashNotesRouter
    fun trashFoldersRouter(entry: NavBackStackEntry): TrashFoldersRouter
}
