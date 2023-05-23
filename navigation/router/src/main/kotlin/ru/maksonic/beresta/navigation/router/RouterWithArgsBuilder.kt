package ru.maksonic.beresta.navigation.router

/**
 * @Author maksonic on 22.05.2023
 */
interface RouterWithArgsBuilder {
    fun buildRouteWithArg(route: String, key: Any): String
    fun buildRouteWithListArgs(route: String, listKeys: List<Any>): String
    fun passArg(route: String, key: Any): String
    fun passListArgs(route: String, listKeys: List<Any>): String

    class Builder : RouterWithArgsBuilder {
        override fun buildRouteWithArg(route: String, key: Any): String =
            route.plus("/{$key}")

        override fun buildRouteWithListArgs(route: String, listKeys: List<Any>): String =
            route.plus(listKeys.joinToString { "/{$it}" }
                .trim()
                .replace(",", "")
            ).filterNot { it.isWhitespace() }

        override fun passArg(route: String, key: Any) = route.plus("/${key}")

        override fun passListArgs(route: String, listKeys: List<Any>) =
            route.plus(listKeys.joinToString { "/${it}" }
                .trim()
                .replace(",", "")
            ).filterNot { it.isWhitespace() }
    }
}