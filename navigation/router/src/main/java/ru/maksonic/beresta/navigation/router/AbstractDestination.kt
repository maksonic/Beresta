package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 15.11.2022
 */
abstract class AbstractDestination(val route: String, argKey: Any = "") {
    val routeWithArg = route.plus("/{$argKey}")
    val passedKey = argKey.toString()
    fun passedArgs(passedKey: Any) = route.plus("/${passedKey}")
}
