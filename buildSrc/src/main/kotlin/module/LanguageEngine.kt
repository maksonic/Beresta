package module

/**
 * @Author maksonic on 14.10.2023
 */
object LanguageEngine {
    object Core : AbstractModule(
        path = ":language-engine:core",
        namespace = "ru.maksonic.beresta.language_engine.core"
    )

    object Shell : AbstractModule(
        path = ":language-engine:shell",
        namespace = "ru.maksonic.beresta.language_engine.shell"
    )
}