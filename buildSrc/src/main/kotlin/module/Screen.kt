package module

/**
 * @Author maksonic on 22.04.2023
 */
object Screen {
    object Folders : AbstractModule(
        path = ":screen:folders",
        namespace = "ru.maksonic.beresta.screen.folders"
    )

    object Main : AbstractModule(
        path = ":screen:main",
        namespace = "ru.maksonic.beresta.screen.main"
    )

    object HiddenNotes : AbstractModule(
        path = ":screen:hidden-notes",
        namespace = "ru.maksonic.beresta.screen.hidden_notes"
    )

    object Settings {
        object Main : AbstractModule(
            path = ":screen:settings",
            namespace = "ru.maksonic.beresta.screen.settings"
        )

        object Appearance : AbstractModule(
            path = ":screen:settings:appearance",
            namespace = "ru.maksonic.beresta.screen.settings.appearance"
        )

        object Notifications : AbstractModule(
            path = ":screen:settings:notifications",
            namespace = "ru.maksonic.beresta.screen.settings.notifications"
        )

        object Security : AbstractModule(
            path = ":screen:settings:security",
            namespace = "ru.maksonic.beresta.screen.settings.security"
        )

        object Tags : AbstractModule(
            path = ":screen:settings:tags",
            namespace = "ru.maksonic.beresta.screen.settings.tags"
        )
    }

    object Splash : AbstractModule(
        path = ":screen:splash",
        namespace = "ru.maksonic.beresta.screen.splash"
    )

    object Trash {
        object Notes : AbstractModule(
            path = ":screen:trash:notes",
            namespace = "ru.maksonic.beresta.screen.trash.notes"
        )

        object Folders : AbstractModule(
            path = ":screen:trash:folders",
            namespace = "ru.maksonic.beresta.screen.trash.folders"
        )
    }
}
