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

        object core : base(
            path = ":feature:splash-screen:core",
            namespace = "ru.maksonic.beresta.feature.splash_screen.core"
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
    }

    object notes {
        object list {
            object api : base(
                path = ":feature:notes:list:api",
                namespace = "ru.maksonic.beresta.feature.notes.list.api"
            )

            object core : base(
                path = ":feature:notes:list:core",
                namespace = "ru.maksonic.beresta.feature.notes.list.core"
            )
        }
        object folders {
            object api : base(
                path = ":feature:notes:folders:api",
                namespace = "ru.maksonic.beresta.feature.notes.folders.api"
            )

            object core : base(
                path = ":feature:notes:folders:core",
                namespace = "ru.maksonic.beresta.feature.notes.folders.core"
            )
        }
    }

    object editNote {
        object api : base(
            path = ":feature:edit-note:api",
            namespace = "ru.maksonic.beresta.feature.edit_note.api"
        )

        object core : base(
            path = ":feature:edit-note:core",
            namespace = "ru.maksonic.beresta.feature.edit_note.core"
        )
    }

    object searchBar {
        object api : base(
            path = ":feature:search-bar:api",
            namespace = "ru.maksonic.beresta.feature.search_bar.api"
        )

        object core : base(
            path = ":feature:search-bar:core",
            namespace = "ru.maksonic.beresta.feature.search_bar.core"
        )
    }

    object topBarCounter {
        object api : base(
            path = ":feature:top-bar-counter:api",
            namespace = "ru.maksonic.beresta.feature.top_bar_counter.api"
        )

        object core : base(
            path = ":feature:top-bar-counter:core",
            namespace = "ru.maksonic.beresta.feature.top_bar_counter.core"
        )
    }
}