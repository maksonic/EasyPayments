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

rootProject.name = "EasyPayments"
include(":app")
include(":common:core")
include(":common:ui")
include(":navigation:router")
include(":navigation:graph")
include(":feature:onboarding")
include(":feature:payments")
include(":feature:auth:api")
include(":feature:auth:core")
include(":feature:auth:ui")
include(":data")
