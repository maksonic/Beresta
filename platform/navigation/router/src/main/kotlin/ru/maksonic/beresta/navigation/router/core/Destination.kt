package ru.maksonic.beresta.navigation.router.core

/**
 * @Author maksonic on 15.11.2022
 */
abstract class AbstractDestination(
    val route: String,
    argKey: Any = "",
    listArgKeys: List<Any> = listOf("")
) {
    private val builder = RouteBuilder.Builder()

    val routeWithArg = builder.buildRouteWithArg(route, argKey)
    val routeWithListArgs = builder.buildRouteWithListArgs(route, listArgKeys)
    val passedKey = argKey.toString()
    val passedKeysList = listArgKeys.map { it.toString() }
    fun passedArgs(key: Any) = builder.buildRouteWithPassArg(route, key)
    fun passedListArgs(keysList: List<Any>) = builder.buildRouteWithPassListArgs(route, keysList)
}

object Destination : AbstractDestination(route = "root") {
    object EditNote : AbstractDestination(
        route = "edit_note", listArgKeys = listOf("isHiddenEditNote", "noteId")
    )

    object Folders : AbstractDestination(route = "folders", argKey = "passedNotesIds")
    object HiddenNotes : AbstractDestination(route = "hidden_notes")
    object Main : AbstractDestination(route = "main")
    object Onboarding : AbstractDestination(route = "onboarding")
    object Settings : AbstractDestination(route = "settings") {
        object Appearance : AbstractDestination(route = "settings.appearance")
        object Notifications : AbstractDestination(route = "settings.notifications")
        object Security : AbstractDestination(route = "settings.security")
    }

    object Splash : AbstractDestination(route = "splash")

    object Trash {
        object Notes : AbstractDestination(route = "trash.notes")
        object Folders : AbstractDestination(route = "trash.folders")
    }
}