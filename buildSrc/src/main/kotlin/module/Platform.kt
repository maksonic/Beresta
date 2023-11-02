package module

/**
 * @Author maksonic on 14.10.2023
 */
object Platform {
    object Core : AbstractModule(
        path = ":platform:core",
        namespace = "ru.maksonic.beresta.platform.core",
    )

    object Elm : AbstractModule(
        path = ":platform:elm",
        namespace = "ru.maksonic.beresta.platform.elm",
    )

    object Navigation {
        object GraphBuilder : AbstractModule(
            path = ":platform:navigation:graph-builder",
            namespace = "ru.maksonic.beresta.plarform.navigation.graph_builder"
        )

        object Router : AbstractModule(
            path = ":platform:navigation:router",
            namespace = "ru.maksonic.beresta.plarform.navigation.router"
        )
    }
}