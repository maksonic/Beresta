package module

/**
 * @Author maksonic on 22.04.2023
 */
object feature {
    object splashScreen {
        object api : base(
            path = ":feature:splash-screen:api",
            namespace = "ru.maksonic.beresta.feature.splash_screen.api"
        )

        object ui : base(
            path = ":feature:splash-screen:ui",
            namespace = "ru.maksonic.beresta.feature.splash_screen.ui"
        )
    }

    object onboarding {
        object api : base(
            path = ":feature:onboarding:api",
            namespace = "ru.maksonic.beresta.feature.onboarding.api"
        )

        object core : base(
            path = ":feature:onboarding:core",
            namespace = "ru.maksonic.beresta.feature.onboarding.core"
        )

        object ui : base(
            path = ":feature:onboarding:ui",
            namespace = "ru.maksonic.beresta.feature.onboarding.ui"
        )
    }

    object languagePicker {
        object api : base(
            path = ":feature:language-picker:api",
            namespace = "ru.maksonic.beresta.feature.language_picker.api"
        )

        object core : base(
            path = ":feature:language-picker:core",
            namespace = "ru.maksonic.beresta.feature.language_picker.core"
        )

        object ui : base(
            path = ":feature:language-picker:ui",
            namespace = "ru.maksonic.beresta.feature.language_picker.ui"
        )
    }

    object themePicker {
        object api : base(
            path = ":feature:theme-picker:api",
            namespace = "ru.maksonic.beresta.feature.theme_picker.api"
        )

        object core : base(
            path = ":feature:theme-picker:core",
            namespace = "ru.maksonic.beresta.feature.theme_picker.core"
        )

        object ui : base(
            path = ":feature:theme-picker:ui",
            namespace = "ru.maksonic.beresta.feature.theme_picker.ui"
        )
    }

    object notes {
        object api : base(
            path = ":feature:notes:api",
            namespace = "ru.maksonic.beresta.feature.notes.api"
        )

        object core : base(
            path = ":feature:notes:core",
            namespace = "ru.maksonic.beresta.feature.notes.core"
        )

        object ui : base(
            path = ":feature:notes:ui",
            namespace = "ru.maksonic.beresta.feature.notes.ui"
        )
    }

    object foldersChipsRow {
        object api : base(
            path = ":feature:folders-chips:api",
            namespace = "ru.maksonic.beresta.feature.folders_chips.api"
        )

        object core : base(
            path = ":feature:folders-chips:core",
            namespace = "ru.maksonic.beresta.feature.folders_chips.core"
        )

        object ui : base(
            path = ":feature:folders-chips:ui",
            namespace = "ru.maksonic.beresta.feature.folders_chips.ui"
        )
    }

    object editNote {
        object api : base(
            path = ":feature:edit-note:api",
            namespace = "ru.maksonic.beresta.feature.edit_note.api"
        )

        object ui : base(
            path = ":feature:edit-note:ui",
            namespace = "ru.maksonic.beresta.feature.edit_note.ui"
        )
    }

    object searchBar {
        object api : base(
            path = ":feature:search-bar:api",
            namespace = "ru.maksonic.beresta.feature.search_bar.api"
        )

        object ui : base(
            path = ":feature:search-bar:ui",
            namespace = "ru.maksonic.beresta.feature.search_bar.ui"
        )
    }

    object sortingSheet {
        object api : base(
            path = ":feature:sorting-sheet:api",
            namespace = "ru.maksonic.beresta.feature.soring_sheet.api"
        )

        object core : base(
            path = ":feature:sorting-sheet:core",
            namespace = "ru.maksonic.beresta.feature.soring_sheet.core"
        )

        object ui : base(
            path = ":feature:sorting-sheet:ui",
            namespace = "ru.maksonic.beresta.feature.soring_sheet.ui"
        )
    }

    object hiddenNotesDialog {
        object api : base(
            path = ":feature:hidden-notes-dialog:api",
            namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.api"
        )

        object core : base(
            path = ":feature:hidden-notes-dialog:core",
            namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.core"
        )

        object ui : base(
            path = ":feature:hidden-notes-dialog:ui",
            namespace = "ru.maksonic.beresta.feature.hidden_notes_dialog.ui"
        )
    }
    object markerColorPicker {
        object api : base(
            path = ":feature:marker-color-picker:api",
            namespace = "ru.maksonic.beresta.feature.marker_color_picker.api"
        )

        object ui : base(
            path = ":feature:marker-color-picker:ui",
            namespace = "ru.maksonic.beresta.feature.marker_color_picker.ui"
        )
    }

    object wallpaperPicker {
        object api : base(
            path = ":feature:wallpaper-picker:api",
            namespace = "ru.maksonic.beresta.feature.wallpaper_picker.api"
        )

        object ui : base(
            path = ":feature:wallpaper-picker:ui",
            namespace = "ru.maksonic.beresta.feature.wallpaper_picker.ui"
        )
    }
}