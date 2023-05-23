package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
object Destination : AbstractDestination(route = "root") {
    object Splash : AbstractDestination(route = "splash")
    object Onboarding : AbstractDestination(route = "onboarding")
    object Main : AbstractDestination(route = "main")
    object EditNote : AbstractDestination(route = "edit_note", argKey = "noteId")
    object Settings : AbstractDestination(route = "settings")
    object TrashList : AbstractDestination(route = "trash_list")
    object NotesFoldersList : AbstractDestination(
        route = "notes_folders_list",
        listArgs = listOf("isMoveNotes", "currentSelectedFolderId")
    )
}