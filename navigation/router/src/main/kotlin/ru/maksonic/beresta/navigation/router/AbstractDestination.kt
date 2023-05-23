package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
abstract class AbstractDestination(
    val route: String,
    argKey: Any = "",
    listArgKeys: List<Any> = listOf("")
) {
    private val builder = RouterWithArgsBuilder.Builder()

    val routeWithArg = builder.buildRouteWithArg(route, argKey)
    val routeWithListArgs = builder.buildRouteWithListArgs(route, listArgKeys)
    val passedKey = argKey.toString()
    val passedKeysList = listArgKeys.map { it.toString() }
    fun passedArgs(key: Any) = builder.buildRouteWithPassArg(route, key)
    fun passedListArgs(keysList: List<Any>) = builder.buildRouteWithPassListArgs(route, keysList)
}