package ru.maksonic.beresta.navigation.graph_builder.navigator

import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
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
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsTagsScreenRouter
import ru.maksonic.beresta.navigation.router.routes.trash.TrashFoldersRouter
import ru.maksonic.beresta.navigation.router.routes.trash.TrashNotesRouter

/**
 * @Author maksonic on 15.11.2022
 */
class AppNavigator : AbstractNavigator() {

    override fun splashRouter(entry: NavBackStackEntry) = SplashRouter(
        toOnboardingOrMain = { verifiedDestination ->
            val destination = verifiedDestination ?: Destination.Onboarding.route
            navigate(entry, destination, isPopUp = false)
        }
    )

    override fun onboardingRouter(entry: NavBackStackEntry) =
        OnboardingRouter { navigate(entry, Destination.Main.route) }

    override fun mainRouter(entry: NavBackStackEntry) = MainRouter(
        toSettings = { navigate(entry, Destination.Settings.route) },
        toTrash = { navigate(entry, Destination.Trash.Notes.route) },
        toNoteEditor = { passedId ->
            val noteId = passedId ?: 0L
            navigate(entry, Destination.EditNote.passedListArgs(listOf(false, noteId)))
        },
        toFoldersList = { ids -> navigate(entry, Destination.Folders.passedArgs(ids)) },
        toHiddenNotes = { navigate(entry, Destination.HiddenNotes.route) }
    )

    override fun editNoteRouter(entry: NavBackStackEntry) = EditNoteRouter(onBack = ::backPressed)

    override fun foldersRouter(entry: NavBackStackEntry) = FoldersRouter(
        onBack = ::backPressed,
        toHiddenNotes = { navigate(entry, Destination.HiddenNotes.route) }
    )

    override fun hiddenNotesRouter(entry: NavBackStackEntry) = HiddenNotesRouter(
        onBack = ::backPressed,
        toNoteEditor = { passedId ->
            val noteId = passedId ?: 0L
            navigate(entry, Destination.EditNote.passedListArgs(listOf(true, noteId)))
        }
    )

    override fun settingsRouter(entry: NavBackStackEntry) = SettingsScreenRouter(
        onBack = ::backPressed,
        toAppearance = { navigate(entry, Destination.Settings.Appearance.route) },
        toNotifications = { navigate(entry, Destination.Settings.Notifications.route) },
        toSecurity = { navigate(entry, Destination.Settings.Security.route) },
        toTags = { navigate(entry, Destination.Settings.Tags.route) },
    )

    override fun settingsAppearanceRouter(entry: NavBackStackEntry) =
        SettingsAppearanceScreenRouter(onBack = ::backPressed)

    override fun settingsNotificationsRouter(entry: NavBackStackEntry) =
        SettingsNotificationsScreenRouter(onBack = ::backPressed)

    override fun settingsSecurityRouter(entry: NavBackStackEntry) =
        SettingsSecurityScreenRouter(onBack = ::backPressed)

    override fun settingsTagsRouter(entry: NavBackStackEntry) =
        SettingsTagsScreenRouter(onBack = ::backPressed)

    override fun trashNotesRouter(entry: NavBackStackEntry) = TrashNotesRouter(
        onBack = ::backPressed,
        toTrashedFoldersList = { navigate(entry, Destination.Trash.Folders.route) }
    )

    override fun trashFoldersRouter(entry: NavBackStackEntry) =
        TrashFoldersRouter(onBack = ::backPressed)

}