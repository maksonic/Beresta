package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */

object Destination : AbstractDestination(route = "root") {
    object Splash : AbstractDestination(route = "splash")
    object Onboarding : AbstractDestination(route = "onboarding")
    object Main : AbstractDestination(route = "main")
    object EditNote : AbstractDestination(
        route = "edit_note", listArgKeys = listOf("isHiddenEditNote", "noteId")
    )

    object Folders : AbstractDestination(route = "folders", argKey = "passedNotesIds")
    object TrashNotesList : AbstractDestination(route = "trash_notes_list")
    object TrashFoldersList : AbstractDestination(route = "trash_folders_list")
    object HiddenNotes : AbstractDestination(route = "hidden_notes", argKey = "passedForHideIds")
    object Settings : AbstractDestination(route = "settings") {
        object Appearance : AbstractDestination(route = "settings.appearance")
        object Security : AbstractDestination(route = "settings.security")
    }
}