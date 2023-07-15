package ru.maksonic.beresta.navigation.graph_builder.navigator

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
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
class AppNavigator : AbstractNavigator() {

    override fun splashRouter(entry: NavBackStackEntry) = SplashScreenRouter(
        toOnboardingOrMain = { verifiedDestination ->
            val destination = verifiedDestination ?: Destination.Onboarding.route
            navigate(entry, destination, isPopUp = false)
        }
    )

    override fun onboardingRouter(entry: NavBackStackEntry) =
        OnboardingRouter { navigate(entry, Destination.Main.route) }

    override fun mainRouter(entry: NavBackStackEntry) = MainScreenRouter(
        toSettings = { navigate(entry, Destination.Settings.route) },
        toTrash = { navigate(entry, Destination.TrashNotesList.route) },
        toNoteEditor = { passedId ->
            val noteId = passedId ?: 0L
            navigate(entry, Destination.EditNote.passedArgs(noteId))
        },
        toFoldersList = { passedListIds ->
            navigate(entry, Destination.Folders.passedArgs(passedListIds))
        }
    )

    override fun settingsRouter(entry: NavBackStackEntry) =
        SettingsScreenRouter(
            onBack = ::backPressed,
            toAppearance = { navigate(entry, Destination.Settings.Appearance.route) }
        )

    override fun settingsAppearanceRouter(entry: NavBackStackEntry) =
        SettingsAppearanceScreenRouter(onBack = ::backPressed)

    override fun editNoteRouter(entry: NavBackStackEntry) =
        EditNoteRouter(onBack = ::backPressed)

    override fun foldersRouter(entry: NavBackStackEntry) =
        FoldersScreenRouter(onBack = ::backPressed)

    override fun trashNotesRouter(entry: NavBackStackEntry) = TrashNotesScreenRouter(
        onBack = ::backPressed,
        toTrashedFoldersList = { navigate(entry, Destination.TrashFoldersList.route) }
    )

    override fun trashFoldersRouter(entry: NavBackStackEntry) =
        TrashFoldersScreenRouter(onBack = ::backPressed)
}