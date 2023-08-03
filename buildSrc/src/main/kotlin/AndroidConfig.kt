import org.gradle.api.JavaVersion

/**
 * @Author maksonic on 20.04.2023
 */
object AndroidConfig {
    const val kotlinCompilerExtensionVersion = "1.5.1"
    const val applicationId = "ru.maksonic.beresta"
    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 26
    const val versionName = "1.0"
    const val versionCode = 1
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val isMinifyEnabled: Boolean = false
    const val isShrinkResources: Boolean = false
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}