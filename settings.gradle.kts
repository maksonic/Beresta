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
include(":app")
include(":database")
include(":language-engine:core")
include(":language-engine:shell")
include(":platform:core")
include(":platform:elm")
include(":platform:navigation:graph-builder")
include(":platform:navigation:router")
// Common
include(":common:core")
include(":common:data")
include(":common:domain")
include(":common:ui-kit")
include(":common:ui-theme")
// Screen
include(":screen:splash")
include(":screen:main")
include(":screen:folders")
include(":screen:hidden-notes")
include(":screen:settings")
include(":screen:settings:appearance")
include(":screen:settings:notifications")
include(":screen:settings:security")
include(":screen:trash:notes")
include(":screen:trash:folders")
// Feature
include(":feature:app-lang:data")
include(":feature:app-lang:domain")

include(":feature:app-theme:data")
include(":feature:app-theme:domain")

include(":feature:folders-list:data")
include(":feature:folders-list:domain")
include(":feature:folders-list:ui:api")
include(":feature:folders-list:ui:core")

include(":feature:hidden-notes-dialog:data")
include(":feature:hidden-notes-dialog:domain")
include(":feature:hidden-notes-dialog:ui:api")
include(":feature:hidden-notes-dialog:ui:core")

include(":feature:marker-color-picker:data")
include(":feature:marker-color-picker:domain")
include(":feature:marker-color-picker:ui:api")
include(":feature:marker-color-picker:ui:core")

include(":feature:notes-list:data")
include(":feature:notes-list:domain")
include(":feature:notes-list:ui:api")
include(":feature:notes-list:ui:core")

include(":feature:onboarding:data")
include(":feature:onboarding:domain")
include(":feature:onboarding:ui:api")
include(":feature:onboarding:ui:core")

include(":feature:sorting-sheet:data")
include(":feature:sorting-sheet:domain")
include(":feature:sorting-sheet:ui:api")
include(":feature:sorting-sheet:ui:core")

include(":feature:tags-list:data")
include(":feature:tags-list:domain")
include(":feature:tags-list:ui:api")
include(":feature:tags-list:ui:core")

include(":feature:wallpaper-picker:data")
include(":feature:wallpaper-picker:domain")
include(":feature:wallpaper-picker:ui:api")
include(":feature:wallpaper-picker:ui:core")
// Feature ui
include(":feature:ui:add-folder-dialog:api")
include(":feature:ui:add-folder-dialog:core")

include(":feature:ui:edit-note:api")
include(":feature:ui:edit-note:core")

include(":feature:ui:language-picker:api")
include(":feature:ui:language-picker:core")

include(":feature:ui:search-bar:api")
include(":feature:ui:search-bar:core")

include(":feature:ui:theme-picker:api")
include(":feature:ui:theme-picker:core")
