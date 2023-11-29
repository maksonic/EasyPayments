/**
 * @Author maksonic on 27.09.2023
 */
sealed class AbstractModule(val path: String, val namespace: String)

object ModuleInfo {
    object App : AbstractModule(
        path = ":app",
        namespace = "ru.maksonic.easypayments"
    )

    object Common {
        object Core : AbstractModule(
            path = ":common:core",
            namespace = "ru.maksonic.easypayments.common.core"
        )

        object Ui : AbstractModule(
            path = ":common:ui",
            namespace = "ru.maksonic.easypayments.common.ui"
        )
    }

    object Data : AbstractModule(
        path = ":data",
        namespace = "ru.maksonic.easypayments.data"
    )

    object Navigation {
        object Graph : AbstractModule(
            path = ":navigation:graph",
            namespace = "ru.maksonic.easypayments.navigation.graph"
        )

        object Router : AbstractModule(
            path = ":navigation:router",
            namespace = "ru.maksonic.easypayments.navigation.router"
        )
    }

    object Feature {
        object Auth {
            object Data : AbstractModule(
                path = ":feature:auth:data",
                namespace = "ru.maksonic.easypayments.feature.auth.data"
            )

            object Domain : AbstractModule(
                path = ":feature:auth:domain",
                namespace = "ru.maksonic.easypayments.feature.auth.domain"
            )

            object Ui : AbstractModule(
                path = ":feature:auth:ui",
                namespace = "ru.maksonic.easypayments.feature.auth.ui"
            )

        }

        object Onboarding {
            object Data : AbstractModule(
                path = ":feature:onboarding:data",
                namespace = "ru.maksonic.easypayments.feature.onboarding.data"
            )
            object Domain : AbstractModule(
                path = ":feature:onboarding:domain",
                namespace = "ru.maksonic.easypayments.feature.onboarding.domain"
            )
            object Ui : AbstractModule(
                path = ":feature:onboarding:ui",
                namespace = "ru.maksonic.easypayments.feature.onboarding.ui"
            )
        }

        object Payments : AbstractModule(
            path = ":feature:payments",
            namespace = "ru.maksonic.easypayments.feature.payments"
        )

        object Settings : AbstractModule(
            path = ":feature:settings",
            namespace = "ru.maksonic.easypayments.feature.settings"
        )
    }
}