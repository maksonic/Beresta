package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
/*abstract class AbstractDestination(
    val route: String,
    argKey: Any = "",
    listKeys: List<Any> = listOf("")
) {
    private val routeBuilder = RouterWithArgsBuilder.Builder()

    val routeWithArg = routeBuilder.buildRouteWithArg(route, argKey)
    val routeWithListArgs = routeBuilder.buildRouteWithListArgs(route, listKeys)
    fun passArg(key: Any) = routeBuilder.passArg(route, key)
    fun passListArgs(keys: List<Any>) = routeBuilder.passListArgs(route, keys)
    val passedKey = argKey.toString()
    val passedListKeys = listKeys.map { it.toString() }
}*/


abstract class AbstractDestination(
    val route: String,
    argKey: Any = "",
    listArgs: List<Any> = listOf("")
) {
    val routeWithArg = route.plus("/{$argKey}")
    val routeWithListArgs = route.plus(listArgs.joinToString { "/{$it}" }
        .trim()
        .replace(",", "")
    ).filterNot { it.isWhitespace() }

    val passedKey = argKey.toString()
    val passedListKey = listArgs.map { it.toString() }
    fun passedArgs(passedKey: Any) = route.plus("/${passedKey}")
    fun passedListArgs(passedKeys: List<Any?>) = route.plus(passedKeys.joinToString { "/${it}" }
        .trim()
        .replace(",", "")
    ).filterNot { it.isWhitespace() }
}
