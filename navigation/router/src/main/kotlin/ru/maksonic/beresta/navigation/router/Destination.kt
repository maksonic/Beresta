package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
object Destination : AbstractDestination(route = "root") {
    object Splash : AbstractDestination(route = "splash")
    object Onboarding : AbstractDestination(route = "onboarding")
    object Main : AbstractDestination(route = "main")
    object EditNote : AbstractDestination(route = "edit_note", argKey = "noteId")
    object Settings : AbstractDestination(route = "settings") {
        object Appearance : AbstractDestination(route = "settings.appearance")
    }

    object TrashNotesList : AbstractDestination(route = "trash_notes_list")
    object TrashFoldersList : AbstractDestination(route = "trash_folders_list")
    object Folders : AbstractDestination(route = "folders", argKey = "passedNotesIds")

}