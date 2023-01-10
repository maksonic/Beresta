/**
 * @Author maksonic on 15.12.2022
 */
object Build {
    private object Version {
        const val toolsGradle = "7.4.0-rc03"
        const val kotlinGradle = Config.kotlinVersion
    }
    object Type {
        private const val RELEASE = "release"
        private const val DEBUG = "debug"
        const val CURRENT = RELEASE
    }

    const val TOOLS_GRADLE = "com.android.tools.build:gradle:${Version.toolsGradle}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinGradle}"
}