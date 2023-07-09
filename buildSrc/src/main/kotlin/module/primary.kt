package module

/**
 * @Author maksonic on 22.04.2023
 */
object primary {
    object app : base(
        path = ":app",
        namespace = "ru.maksonic.beresta",
    )

    object core : base(
        path = ":core",
        namespace = "ru.maksonic.beresta.core",
    )

    object data {
        object common : base(
            path = ":data:common",
            namespace = "ru.maksonic.beresta.data.common"
        )

        object database : base(
            path = ":data:database",
            namespace = "ru.maksonic.beresta.data.database"
        )

        object notes : base(
            path = ":data:notes",
            namespace = "ru.maksonic.beresta.data.notes"
        )
        object folders : base(
            path = ":data:folders",
            namespace = "ru.maksonic.beresta.data.folders"
        )
    }


    object elm : base(
        path = ":elm",
        namespace = "ru.maksonic.beresta.elm",
    )

    object languageEngine {
        object core : base(
            path = ":language-engine:core",
            namespace = "ru.maksonic.beresta.language_engine.core"
        )

        object shell : base(
            path = ":language-engine:shell",
            namespace = "ru.maksonic.beresta.language_engine.shell"

        )
    }

    object navigation {
        object graphBuilder : base(
            path = ":navigation:graph-builder",
            namespace = "ru.maksonic.beresta.navigation.graph_builder"
        )

        object router : base(
            path = ":navigation:router",
            namespace = "ru.maksonic.beresta.navigation.router"
        )
    }

    object ui {
        object theme : base(
            path = ":ui:theme",
            namespace = "ru.maksonic.beresta.ui.theme"
        )

        object widget : base(
            path = ":ui:widget",
            namespace = "ru.maksonic.beresta.ui.widget"
        )
    }
}