/**
 * @Author maksonic on 20.04.2023
 */
object buildConfig {
    val gradleVersion = "8.0.0"
    object type {
        private const val RELEASE = "release"
        private const val DEBUG = "debug"
        val current = RELEASE
    }
}