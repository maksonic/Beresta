import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * @Author maksonic on 15.12.2022
 */
fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpec.ksp(): PluginDependencySpec =
    id("com.google.devtools.ksp") version Config.kspVersion
