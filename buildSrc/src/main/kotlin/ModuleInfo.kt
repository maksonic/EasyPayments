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
            object Api : AbstractModule(
                path = ":feature:auth:api",
                namespace = "ru.maksonic.easypayments.feature.auth.api"
            )

            object Core : AbstractModule(
                path = ":feature:auth:core",
                namespace = "ru.maksonic.easypayments.feature.auth.core"
            )

            object Ui : AbstractModule(
                path = ":feature:auth:ui",
                namespace = "ru.maksonic.easypayments.feature.auth.ui"
            )

        }

        object Onboarding : AbstractModule(
            path = ":feature:onboarding",
            namespace = "ru.maksonic.easypayments.feature.onboarding"
        )

        object Payments : AbstractModule(
            path = ":feature:payments",
            namespace = "ru.maksonic.easypayments.feature.payments"
        )
    }
}