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
include(":data")
include(":common:core")
include(":common:ui")
include(":navigation:router")
include(":navigation:graph")
include(":feature:auth:data")
include(":feature:auth:domain")
include(":feature:auth:ui")
include(":feature:payments")
include(":feature:onboarding:data")
include(":feature:onboarding:domain")
include(":feature:onboarding:ui")
include(":feature:settings")
