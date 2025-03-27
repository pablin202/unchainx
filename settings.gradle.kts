pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "UnchainX"
include(":app")
include(":designsystems")
include(":feature:main:presentation")
include(":storage:datastore")
include(":common:model")
include(":feature:onboarding:presentation")
include(":feature:onboarding:domain")
include(":feature:onboarding:data")
include(":feature:main:domain")
include(":feature:main:data")
include(":feature:wallet:presentation")
include(":crypto:core")
include(":feature:wallet:domain")
include(":feature:wallet:data")
include(":storage:preferences")
