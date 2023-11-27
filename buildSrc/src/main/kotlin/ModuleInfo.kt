/**
 * @Author maksonic on 27.09.2023
 */
sealed class AbstractModule(val path: String, val namespace: String)

object ModuleInfo {
    object App : AbstractModule(
        path = ":app",
        namespace = "ru.maksonic.easypayments"
    )
}