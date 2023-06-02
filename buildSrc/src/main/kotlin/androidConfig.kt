import org.gradle.api.JavaVersion

/**
 * @Author maksonic on 20.04.2023
 */
object androidConfig {
    val kotlinVersion = "1.8.21"
    val kotlinCompilerExtensionVersion = "1.4.7"
    val kspVersion = "1.8.21-1.0.11"
    val applicationId = "ru.maksonic.beresta"
    val compileSdk = 33
    val targetSdk = 33
    val minSdk = 26
    val versionName = "1.0"
    val versionCode = 1
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val androidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    val isMinifyEnabled: Boolean = false
    val isShrinkResources: Boolean = false
    val javaVersion = JavaVersion.VERSION_17
    val jvmTarget = "17"
}