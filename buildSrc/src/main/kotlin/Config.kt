import org.gradle.api.JavaVersion

/**
 * @Author maksonic on 15.12.2022
 */
object Config {
    const val kotlinVersion = "1.8.10"
    const val composeVersion = "1.4.0-alpha04"
    const val kcExtVersion = "1.4.3"
    const val kspVersion = "1.8.10-1.0.9"
    const val appId = "ru.maksonic.beresta"
    const val compileSdk = 33
    const val targetSdk = 33
    const val minSdk = 23
    const val versionName = "1.0"
    const val versionCode = 1
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val androidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val isMinifyEnabled: Boolean = false
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}