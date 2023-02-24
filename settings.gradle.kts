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
include (":app")
include(":core")
include(":data:common")
include(":data:database")
include(":data:notes")
include(":elm")
include(":navigation:graph-builder")
include(":navigation:router")
include(":ui:theme")
include(":ui:widget")

include(":screen:main")
include(":screen:settings")
include(":screen:trash-list")

include(":feature:onboarding:api")
include(":feature:onboarding:core")

include(":feature:splash-screen:api")
include(":feature:splash-screen:core")

include(":feature:theme-selector:api")
include(":feature:theme-selector:core")

include(":feature:language-selector:api")
include(":feature:language-selector:core")

include(":feature:notes-list:api")
include(":feature:notes-list:core")

include(":feature:search-bar:api")
include(":feature:search-bar:core")

include(":feature:edit-note:api")
include(":feature:edit-note:core")