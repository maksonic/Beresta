package ru.maksonic.beresta.navigation.router.navigator

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.Router
import ru.maksonic.beresta.navigation.router.router.*

/**
 * @Author maksonic on 15.11.2022
 */
class AppNavigator : AbstractNavigator(), Router {

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
        toTrash = { navigate(entry, Destination.TrashList.route) },
        toNoteEditor = { passedId ->
            val noteId = passedId ?: 0L
            navigate(entry, Destination.EditNote.passedArgs(noteId))
        },
        toFoldersList = { isMoveNotesToFolderState, currentSelectedFolderId ->
            val args = listOf(isMoveNotesToFolderState, currentSelectedFolderId)
            navigate(entry, Destination.NotesFoldersList.passedListArgs(args))
        }
    )

    override fun editNoteRouter(entry: NavBackStackEntry) = EditNoteRouter(onBack = ::backPressed)

    override fun settingsRouter(entry: NavBackStackEntry) = SettingsScreenRouter(
        onBack = ::backPressed
    )

    override fun trashRouter(entry: NavBackStackEntry) = TrashScreenRouter(
        onBack = ::backPressed
    )

    override fun foldersRouter(entry: NavBackStackEntry) = NotesFoldersScreenRouter(
        onBack = ::backPressed
    )
}