/**
 * @Author maksonic on 15.12.2022
 */
sealed class BaseModule(val path: String, val namespace: String) {
    object App : BaseModule(
        path = ":app",
        namespace = "ru.maksonic.beresta",
    )

    object Core : BaseModule(
        path = ":core",
        namespace = "ru.maksonic.beresta.core",
    )

    object Data {
        object Common : BaseModule(
            path = ":data:common",
            namespace = "ru.maksonic.beresta.data.common"
        )

        object Database : BaseModule(
            path = ":data:database",
            namespace = "ru.maksonic.beresta.data.database"
        )
    }

    object Elm : BaseModule(
        path = ":elm",
        namespace = "ru.maksonic.beresta.elm",
    )

    object Navigation {
        object GraphBuilder : BaseModule(
            path = ":navigation:graph-builder",
            namespace = "ru.maksonic.beresta.navigation.graph_builder"
        )

        object Router : BaseModule(
            path = ":navigation:router",
            namespace = "ru.maksonic.beresta.navigation.router"
        )
    }

    object Screen {
        object Main : BaseModule(
            path = ":screen:main",
            namespace = "ru.maksonic.beresta.screen.main"
        )

        object Settings : BaseModule(
            path = ":screen:settings",
            namespace = "ru.maksonic.beresta.screen.settings"
        )
    }

    object Feature {

        object Onboarding {
            object Data : BaseModule(
                path = ":feature:onboarding:data",
                namespace = "ru.maksonic.beresta.feature.onboarding.data"
            )

            object Domain : BaseModule(
                path = ":feature:onboarding:domain",
                namespace = "ru.maksonic.beresta.feature.onboarding.domain"
            )

            object Ui : BaseModule(
                path = ":feature:onboarding:ui",
                namespace = "ru.maksonic.beresta.feature.onboarding.ui"
            )
        }

        object SplashScreen : BaseModule(
            path = ":feature:splash-screen",
            namespace = "ru.maksonic.beresta.feature.splash_screen"
        )

        object ThemeSelector : BaseModule(
            path = ":feature:theme-selector",
            namespace = "ru.maksonic.beresta.feature.theme_selector"
        )

        object NotesList {
            object Data : BaseModule(
                path = ":feature:notes-list:data",
                namespace = "ru.maksonic.beresta.feature.notes_list.data"
            )

            object Domain : BaseModule(
                path = ":feature:notes-list:domain",
                namespace = "ru.maksonic.beresta.feature.notes_list.domain"
            )

            object Ui : BaseModule(
                path = ":feature:notes-list:ui",
                namespace = "ru.maksonic.beresta.feature.notes_list.ui"
            )
        }

        object TasksList {
            object Data : BaseModule(
                path = ":feature:tasks-list:data",
                namespace = "ru.maksonic.beresta.feature.tasks_list.data"
            )

            object Domain : BaseModule(
                path = ":feature:tasks-list:domain",
                namespace = "ru.maksonic.beresta.feature.tasks_list.domain"
            )

            object Ui : BaseModule(
                path = ":feature:tasks-list:ui",
                namespace = "ru.maksonic.beresta.feature.tasks_list.ui"
            )
        }
    }

    object Ui {
        object Theme : BaseModule(
            path = ":ui:theme",
            namespace = "ru.maksonic.beresta.ui.theme"
        )

        object Widget : BaseModule(
            path = ":ui:widget",
            namespace = "ru.maksonic.beresta.ui.widget"
        )
    }
}