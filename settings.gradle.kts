pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "example-project"
include(
    ":app",
    ":core:networking",
    ":core:coroutines",
    ":core:design",
    ":core:testing:mockwebserver",
    ":core:testing:endpoints",
    ":data:product",
    ":feature:start",
    ":feature:main",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
