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

    object Domain : AbstractModule(
        path = ":domain",
        namespace = "ru.maksonic.easypayments.domain"
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
}