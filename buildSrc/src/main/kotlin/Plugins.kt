import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * @Author maksonic on 20.04.2023
 */

fun PluginDependenciesSpec.pluginAndroidApp(): PluginDependencySpec =
    id("com.android.application") version (buildConfig.gradleVersion) apply false

fun PluginDependenciesSpec.pluginAndroidLibrary(): PluginDependencySpec =
    id("com.android.library") version (buildConfig.gradleVersion) apply false

fun PluginDependenciesSpec.pluginKotlinAndroid(): PluginDependencySpec =
    kotlin("android") version (androidConfig.kotlinVersion) apply false

fun PluginDependenciesSpec.pluginKotlinSerialization(): PluginDependencySpec =
    kotlin("plugin.serialization") version (androidConfig.kotlinVersion) apply false

fun PluginDependenciesSpec.ksp(): PluginDependencySpec =
    id("com.google.devtools.ksp") version androidConfig.kspVersion

fun PluginDependenciesSpec.androidApp(): PluginDependencySpec = id("com.android.application")
fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec = id("com.android.library")
fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec = kotlin("android")
fun PluginDependenciesSpec.kotlinSerialization(): PluginDependencySpec = id("kotlinx-serialization")