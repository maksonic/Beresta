pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Beresta"
//primary
include (":app")
include (":core")
include(":data:common")
include(":data:database")
include(":data:notes")
include(":data:folders")
include(":elm")
include(":ui:theme")
include(":ui:widget")
include(":navigation:graph-builder")
include(":navigation:router")
include(":language-engine:core")
include(":language-engine:shell")
//common
include(":common:json-converter")
include(":common:coroutine-dispatchers")
//screens
include(":screen:main")
include(":screen:settings")
include(":screen:settings:appearance")
include(":screen:folders")
include(":screen:trash-list:notes")
include(":screen:trash-list:folders")

//features
include(":feature:splash-screen:api")
include(":feature:splash-screen:ui")

include(":feature:onboarding:api")
include(":feature:onboarding:core")
include(":feature:onboarding:ui")

include(":feature:theme-picker:api")
include(":feature:theme-picker:core")
include(":feature:theme-picker:ui")

include(":feature:language-picker:api")
include(":feature:language-picker:core")
include(":feature:language-picker:ui")

include(":feature:notes:api")
include(":feature:notes:core")
include(":feature:notes:ui")

include(":feature:folders-chips:api")
include(":feature:folders-chips:core")
include(":feature:folders-chips:ui")

include(":feature:edit-note:api")
include(":feature:edit-note:ui")

include(":feature:search-bar:api")
include(":feature:search-bar:ui")

include(":feature:top-bar-counter:api")
include(":feature:top-bar-counter:ui")

include(":feature:sorting-sheet:api")
include(":feature:sorting-sheet:core")
include(":feature:sorting-sheet:ui")
