import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * @Author maksonic on 15.12.2022
 */
fun PluginDependenciesSpec.pluginAndroidApp(): PluginDependencySpec =
    id("com.android.application") version ("7.4.1") apply false

fun PluginDependenciesSpec.pluginAndroidLibrary(): PluginDependencySpec =
    id("com.android.library") version ("7.4.1") apply false

fun PluginDependenciesSpec.pluginKotlinAndroid(): PluginDependencySpec =
    kotlin("android") version (Build.Version.KOTLIN_GRADLE) apply false

fun PluginDependenciesSpec.pluginKotlinSerialization(): PluginDependencySpec =
    kotlin("plugin.serialization") version (Build.Version.KOTLIN_GRADLE) apply false

fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpec.kotlinSerialization(): PluginDependencySpec =
    id("kotlinx-serialization")

fun PluginDependenciesSpec.ksp(): PluginDependencySpec =
    id("com.google.devtools.ksp") version Config.kspVersion

fun PluginDependenciesSpec.parcelize(): PluginDependencySpec =
    id("kotlin-parcelize")
