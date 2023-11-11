package module

/**
 * @Author maksonic on 27.09.2023
 */
object Feature {
    object AppLang {
        object Data : AbstractModule(
            path = ":feature:app-lang:data",
            namespace = "ru.maksonic.beresta.feature.app_lang.data"
        )

        object Domain : AbstractModule(
            path = ":feature:app-lang:domain",
            namespace = "ru.maksonic.beresta.feature.app_lang.domain"
        )
    }

    object AddFolderDialog {
        object Ui {
            object Api : AbstractModule(
                path = ":feature:ui:add-folder-dialog:api",
                namespace = "ru.maksonic.beresta.feature.ui.add_folder_dialog.api"
            )

            object Core : AbstractModule(
                path = ":feature:ui:add-folder-dialog:core",
                namespace = "ru.maksonic.beresta.feature.ui.add_folder_dialog.core"
            )
        }
    }

    object AppTheme {
        object Data : AbstractModule(
            path = ":feature:app-theme:data",
            namespace = "ru.maksonic.beresta.feature.app_theme.data"
        )

        object Domain : AbstractModule(
            path = ":feature:app-theme:domain",
            namespace = "ru.maksonic.beresta.feature.app_theme.domain"
        )
    }

    object EditNote {
        object Ui {
            object Api : AbstractModule(
                path = ":feature:ui:edit-note:api",
                namespace = "ru.maksonic.beresta.feature.ui.edit_note.api"
            )

            object Core : AbstractModule(
                path = ":feature:ui:edit-note:core",
                namespace = "ru.maksonic.beresta.feature.ui.edit_note.core"
            )
        }
    }

    object HiddenNotesDialog {
        object Data : AbstractModule(
            path = ":feature:hidden-notes-dialog:data",
            namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.data"
        )

        object Domain : AbstractModule(
            path = ":feature:hidden-notes-dialog:domain",
            namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:hidden-notes-dialog:ui:api",
                namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:hidden-notes-dialog:ui:core",
                namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core"
            )
        }
    }

    object Onboarding {
        object Data : AbstractModule(
            path = ":feature:onboarding:data",
            namespace = "ru.maksonic.beresta.feature.onboarding.data"
        )

        object Domain : AbstractModule(
            path = ":feature:onboarding:domain",
            namespace = "ru.maksonic.beresta.feature.onboarding.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:onboarding:ui:api",
                namespace = "ru.maksonic.beresta.feature.onboarding.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:onboarding:ui:core",
                namespace = "ru.maksonic.beresta.feature.onboarding.ui.core"
            )
        }
    }

    object LanguagePicker {
        object Ui {
            object Api : AbstractModule(
                path = ":feature:ui:language-picker:api",
                namespace = "ru.maksonic.beresta.feature.ui.language_picker.api"
            )

            object Core : AbstractModule(
                path = ":feature:ui:language-picker:core",
                namespace = "ru.maksonic.beresta.feature.ui.language_picker.core"
            )
        }
    }

    object FoldersList {
        object Data : AbstractModule(
            path = ":feature:folders-list:data",
            namespace = "ru.maksonic.beresta.feature.folders_list.data"
        )

        object Domain : AbstractModule(
            path = ":feature:folders-list:domain",
            namespace = "ru.maksonic.beresta.feature.folders_list.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:folders-list:ui:api",
                namespace = "ru.maksonic.beresta.feature.folders_list.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:folders-list:ui:core",
                namespace = "ru.maksonic.beresta.feature.folders_list.ui.core"
            )
        }
    }

    object MarkerColorPicker {
        object Data : AbstractModule(
            path = ":feature:marker-color-picker:data",
            namespace = "ru.maksonic.beresta.feature.marker_color_picker.data"
        )

        object Domain : AbstractModule(
            path = ":feature:marker-color-picker:domain",
            namespace = "ru.maksonic.beresta.feature.marker_color_picker.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:marker-color-picker:ui:api",
                namespace = "ru.maksonic.beresta.feature.marker_color_picker.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:marker-color-picker:ui:core",
                namespace = "ru.maksonic.beresta.feature.marker_color_picker.ui.core"
            )
        }
    }

    object NotesList {
        object Data : AbstractModule(
            path = ":feature:notes-list:data",
            namespace = "ru.maksonic.beresta.feature.notes_list.data"
        )

        object Domain : AbstractModule(
            path = ":feature:notes-list:domain",
            namespace = "ru.maksonic.beresta.feature.notes_list.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:notes-list:ui:api",
                namespace = "ru.maksonic.beresta.feature.notes_list.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:notes-list:ui:core",
                namespace = "ru.maksonic.beresta.feature.notes_list.ui.core"
            )
        }
    }

    object SearchBar {
        object Ui {
            object Api : AbstractModule(
                path = ":feature:ui:search-bar:api",
                namespace = "ru.maksonic.beresta.feature.ui.search_bar.api"
            )

            object Core : AbstractModule(
                path = ":feature:ui:search-bar:core",
                namespace = "ru.maksonic.beresta.feature.ui.search_bar.core"
            )
        }
    }

    object SortingSheet {
        object Data : AbstractModule(
            path = ":feature:sorting-sheet:data",
            namespace = "ru.maksonic.beresta.feature.sorting_sheet.data"
        )

        object Domain : AbstractModule(
            path = ":feature:sorting-sheet:domain",
            namespace = "ru.maksonic.beresta.feature.sorting_sheet.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:sorting-sheet:ui:api",
                namespace = "ru.maksonic.beresta.feature.sorting_sheet.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:sorting-sheet:ui:core",
                namespace = "ru.maksonic.beresta.feature.sorting_sheet.ui.core"
            )
        }
    }

    object TagsList {
        object Data : AbstractModule(
            path = ":feature:tags-list:data",
            namespace = "ru.maksonic.beresta.feature.tags_list.data"
        )

        object Domain : AbstractModule(
            path = ":feature:tags-list:domain",
            namespace = "ru.maksonic.beresta.feature.tags_list.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:tags-list:ui:api",
                namespace = "ru.maksonic.beresta.feature.tags_list.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:tags-list:ui:core",
                namespace = "ru.maksonic.beresta.feature.tags_list.ui.core"
            )
        }
    }

    object ThemePicker {
        object Ui {
            object Api : AbstractModule(
                path = ":feature:ui:theme-picker:api",
                namespace = "ru.maksonic.beresta.feature.ui.theme_picker.api"
            )

            object Core : AbstractModule(
                path = ":feature:ui:theme-picker:core",
                namespace = "ru.maksonic.beresta.feature.ui.theme_picker.core"
            )
        }
    }

    object WallpaperPicker {
        object Data : AbstractModule(
            path = ":feature:wallpaper-picker:data",
            namespace = "ru.maksonic.beresta.feature.wallpaper_picker.data"
        )

        object Domain : AbstractModule(
            path = ":feature:wallpaper-picker:domain",
            namespace = "ru.maksonic.beresta.feature.wallpaper_picker.domain"
        )

        object Ui {
            object Api : AbstractModule(
                path = ":feature:wallpaper-picker:ui:api",
                namespace = "ru.maksonic.beresta.feature.wallpaper_picker.ui.api"
            )

            object Core : AbstractModule(
                path = ":feature:wallpaper-picker:ui:core",
                namespace = "ru.maksonic.beresta.feature.wallpaper_picker.ui.core"
            )
        }
    }
}