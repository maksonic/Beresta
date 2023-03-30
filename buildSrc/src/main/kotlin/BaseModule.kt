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

        object Notes : BaseModule(
            path = ":data:notes",
            namespace = "ru.maksonic.beresta.data.notes"
        )
        object NotesFolders : BaseModule(
            path = ":data:notes-folders",
            namespace = "ru.maksonic.beresta.data.notes_folders"
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

        object EditNote : BaseModule(
            path = ":screen:edit-note",
            namespace = "ru.maksonic.beresta.screen.edit_note"
        )

        object Settings : BaseModule(
            path = ":screen:settings",
            namespace = "ru.maksonic.beresta.screen.settings"
        )

        object Trash : BaseModule(
            path = ":screen:trash-list",
            namespace = "ru.maksonic.beresta.screen.trash_list"
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

            object Core : BaseModule(
                path = ":feature:notes-list:core",
                namespace = "ru.maksonic.beresta.feature.notes_list.core"
            )
        }

        object NoteWallpaperSelector {
            object Api : BaseModule(
                path = ":feature:note-wallpaper-selector:api",
                namespace = "ru.maksonic.beresta.feature.note_wallpaper_selector.api"
            )

            object Core : BaseModule(
                path = ":feature:note-wallpaper-selector:core",
                namespace = "ru.maksonic.beresta.feature.note_wallpaper_selector.core"
            )
        }

        object SearchBar {
            object Api : BaseModule(
                path = ":feature:search-bar:api",
                namespace = "ru.maksonic.beresta.feature.search_bar.api"
            )

            object Core : BaseModule(
                path = ":feature:search-bar:core",
                namespace = "ru.maksonic.beresta.feature.search_bar.core"
            )
        }


        object TrashList {
            object Api : BaseModule(
                path = ":feature:trash-list:api",
                namespace = "ru.maksonic.beresta.feature.trash_list.api"
            )

            object Core : BaseModule(
                path = ":feature:trash-list:core",
                namespace = "ru.maksonic.beresta.feature.trash_list.core"
            )
        }

        object EditNote {
            object Api : BaseModule(
                path = ":feature:edit-note:api",
                namespace = "ru.maksonic.beresta.feature.edit_note.api"
            )

            object Core : BaseModule(
                path = ":feature:edit-note:core",
                namespace = "ru.maksonic.beresta.feature.edit_note.core"
            )
        }

        object FoldersList {
            object Api : BaseModule(
                path = ":feature:folders-list:api",
                namespace = "ru.maksonic.beresta.feature.folders_list.api"
            )

            object Core : BaseModule(
                path = ":feature:folders-list:core",
                namespace = "ru.maksonic.beresta.feature.folders_list.core"
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