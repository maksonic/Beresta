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
        namespace = "ru.maksonic.beresta.elm"
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
            object Api : BaseModule(
                path = ":feature:onboarding:api",
                namespace = "ru.maksonic.beresta.feature.onboarding.api"
            )

            object Core : BaseModule(
                path = ":feature:onboarding:core",
                namespace = "ru.maksonic.beresta.feature.onboarding.core"
            )
        }

        object SplashScreen {
            object Api : BaseModule(
                path = ":feature:splash-screen:api",
                namespace = "ru.maksonic.beresta.feature.splash_screen.api"
            )

            object Core : BaseModule(
                path = ":feature:splash-screen:core",
                namespace = "ru.maksonic.beresta.feature.splash_screen.core"
            )
        }

        object ThemeSelector {
            object Api : BaseModule(
                path = ":feature:theme-selector:api",
                namespace = "ru.maksonic.beresta.feature.theme_selector.api"
            )
            object Core : BaseModule(
                path = ":feature:theme-selector:core",
                namespace = "ru.maksonic.beresta.feature.theme_selector.core"
            )
        }

        object LanguageSelector {
            object Api : BaseModule(
                path = ":feature:language-selector:api",
                namespace = "ru.maksonic.beresta.feature.language_selector.api"
            )
            object Core : BaseModule(
                path = ":feature:language-selector:core",
                namespace = "ru.maksonic.beresta.feature.language_selector.core"
            )
        }

        object NotesList {
            object Api : BaseModule(
                path = ":feature:notes-list:api",
                namespace = "ru.maksonic.beresta.feature.notes_list.api"
            )

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
            object Api : BaseModule(
                path = ":feature:tasks-list:api",
                namespace = "ru.maksonic.beresta.feature.tasks_list.api"
            )

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

        object TrashList {
            object Domain : BaseModule(
                path = ":feature:trash-list:domain",
                namespace = "ru.maksonic.beresta.feature.trash_list.domain"
            )

            object Ui : BaseModule(
                path = ":feature:trash-list:ui",
                namespace = "ru.maksonic.beresta.feature.trash_list.ui"
            )
        }

        object BottomPanel {
            object Api : BaseModule(
                path = ":feature:bottom-panel:api",
                namespace = "ru.maksonic.beresta.feature.bottom_panel.api"
            )

            object Ui : BaseModule(
                path = ":feature:bottom-panel:ui",
                namespace = "ru.maksonic.beresta.feature.bottom_panel.ui"
            )
        }

        object EditNote {
            object Domain : BaseModule(
                path = ":feature:edit-note:domain",
                namespace = "ru.maksonic.beresta.feature.edit_note.domain"
            )

            object Ui : BaseModule(
                path = ":feature:edit-note:ui",
                namespace = "ru.maksonic.beresta.feature.edit_note.ui"
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