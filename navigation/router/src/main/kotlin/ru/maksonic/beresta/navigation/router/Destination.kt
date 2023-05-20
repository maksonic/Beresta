package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
object Destination : AbstractDestination("root") {
    object Splash : AbstractDestination("splash")
    object Onboarding : AbstractDestination("onboarding")
    object Main : AbstractDestination("main")
    object EditNote : AbstractDestination("edit_note", argKey = "noteId")
    object Settings : AbstractDestination("settings")
    object TrashList : AbstractDestination("trash_list")
    object NotesFoldersList : AbstractDestination("notes_folders_list", argKey = "isMoveNotes")
}