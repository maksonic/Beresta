package module

/**
 * @Author maksonic on 14.10.2023
 */
object Common {
    object Core : AbstractModule(
        path = ":common:core",
        namespace = "ru.maksonic.common.core"
    )

    object Data : AbstractModule(
        path = ":common:data",
        namespace = "ru.maksonic.common.data"
    )

    object Domain : AbstractModule(
        path = ":common:domain",
        namespace = "ru.maksonic.common.domain"
    )

    object UiKit : AbstractModule(
        path = ":common:ui-kit",
        namespace = "ru.maksonic.beresta.common.ui_kit"
    )

    object UiTheme : AbstractModule(
        path = ":common:ui-theme",
        namespace = "ru.maksonic.beresta.common.ui_theme"
    )
}