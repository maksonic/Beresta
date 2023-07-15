package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 27.04.2023
 */
val LocalAppAnimationVelocity = staticCompositionLocalOf<AppAnimationVelocity> {
    error("No animations velocity provided")
}
data class NavigationVelocity(
    val fade: Int,
    val slide: Int
)

data class AppAnimationVelocity(
    val current: Key,
    val searchBarTransform: Int,
    val createNoteFabExpand: Int,
    val fadeIn: Int,
    val fadeOut: Int,
    val common: Int,
    val dialogVisibility: Int,
    val disabled: Int,
    val navigationVelocity: NavigationVelocity
) {
    enum class Key(val value: Float) {
        DISABLE(0F), SLOW(1F), NORMAL(2F), FAST(3F), VERY_FAST(4F)
    }

    companion object {
        private const val DISABLE = 0
        private const val SLOW = 600
        private const val NORMAL = 400
        private const val FAST = 250
        private const val VERY_FAST = 150

        val Disabled = AppAnimationVelocity(
            current = Key.DISABLE,
            searchBarTransform = DISABLE,
            createNoteFabExpand = DISABLE,
            fadeIn = DISABLE,
            fadeOut = DISABLE,
            common = DISABLE,
            dialogVisibility = DISABLE,
            disabled = DISABLE,
            navigationVelocity = NavigationVelocity(DISABLE, DISABLE)
        )
        val Slow = AppAnimationVelocity(
            current = Key.SLOW,
            searchBarTransform = SLOW,
            createNoteFabExpand = 650,
            fadeIn = SLOW,
            fadeOut = SLOW,
            common = SLOW,
            dialogVisibility = 400,
            disabled = DISABLE,
            navigationVelocity = NavigationVelocity(SLOW, SLOW)
        )
        val Normal = AppAnimationVelocity(
            current = Key.NORMAL,
            searchBarTransform = NORMAL,
            createNoteFabExpand = 500,
            fadeIn = NORMAL,
            fadeOut = NORMAL,
            common = NORMAL,
            dialogVisibility = 200,
            disabled = DISABLE,
            navigationVelocity = NavigationVelocity(300, 300)
        )
        val Fast = AppAnimationVelocity(
            current = Key.FAST,
            searchBarTransform = FAST,
            createNoteFabExpand = 350,
            fadeIn = FAST,
            fadeOut = FAST,
            common = FAST,
            dialogVisibility = 150,
            disabled = DISABLE,
            navigationVelocity = NavigationVelocity(200, 200)
        )

        val VeryFast = AppAnimationVelocity(
            current = Key.VERY_FAST,
            searchBarTransform = VERY_FAST,
            createNoteFabExpand = 250,
            fadeIn = VERY_FAST,
            fadeOut = VERY_FAST,
            common = VERY_FAST,
            dialogVisibility = 90,
            disabled = DISABLE,
            navigationVelocity = NavigationVelocity(100, 100)
        )
    }
}

val AppAnimationVelocity.Key.isDisabled get() = this == AppAnimationVelocity.Key.DISABLE