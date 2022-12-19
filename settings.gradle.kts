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
include(":screen:main")
include(":screen:settings")
include(":navigation:graph-builder")
include(":navigation:router")
include(":ui:theme")
include(":ui:widget")
include(":core")
include(":elm")
include(":data:common")
include(":data:database")
include(":feature:splash-screen")
include(":feature:theme-selector")
include(":feature:onboarding:data")
include(":feature:onboarding:domain")
include(":feature:onboarding:ui")
include(":feature:notes-list:ui")
include(":feature:notes-list:domain")
include(":feature:notes-list:data")
include(":feature:tasks-list:ui")
include(":feature:tasks-list:domain")
include(":feature:tasks-list:data")
include(":feature:onboarding:data")
include(":feature:onboarding:domain")
include(":feature:onboarding:ui")
