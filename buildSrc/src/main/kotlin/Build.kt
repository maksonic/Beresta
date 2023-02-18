/**
 * @Author maksonic on 15.12.2022
 */
object Build {
    object Version {
        const val TOOLS_GRADLE = "8.0.0-beta02"
        const val KOTLIN_GRADLE = Config.kotlinVersion
    }
    object Type {
        private const val RELEASE = "release"
        private const val DEBUG = "debug"
        const val CURRENT = RELEASE
    }
}