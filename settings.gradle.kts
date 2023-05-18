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
include(":data:notes-folders")
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
include(":screen:trash-list")
//features
include(":feature:splash-screen:api")
include(":feature:splash-screen:core")
include(":feature:onboarding:api")
include(":feature:onboarding:core")
include(":feature:language-picker:api")
include(":feature:language-picker:core")
include(":feature:theme-picker:api")
include(":feature:theme-picker:core")
include(":feature:notes:list:api")
include(":feature:notes:list:core")
include(":feature:notes:folders:api")
include(":feature:notes:folders:core")

include(":feature:edit-note:api")
include(":feature:edit-note:core")
include(":feature:search-bar:api")
include(":feature:search-bar:core")
include(":feature:top-bar-counter")
include(":feature:top-bar-counter:api")
include(":feature:top-bar-counter:core")
