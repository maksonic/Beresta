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
    object TrashNotesList : AbstractDestination(route = "trash_notes_list")
    object TrashFoldersList : AbstractDestination(route = "trash_folders_list")
    object NotesFoldersList : AbstractDestination(
        route = "notes_folders_list",
        listArgKeys = listOf("isMoveNotes", "currentSelectedFolderId")
    )
}