import org.gradle.api.JavaVersion

/**
 * @Author maksonic on 27.09.2023
 */
object AndroidConfig {
    const val APP_ID = "ru.maksonic.easypayments"
    const val COMPILE_SDK = 34
    const val TARGET_SDK = 34
    const val MIN_SDK = 26
    const val VERSION_NAME = "1.0"
    const val VERSION_CODE = 1
    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val IS_MINIFY: Boolean = false
    const val IS_SHRINK_RESOURCES: Boolean = false
    val JAVA_VERSION = JavaVersion.VERSION_17
    const val JVM_TARGET = "17"
}