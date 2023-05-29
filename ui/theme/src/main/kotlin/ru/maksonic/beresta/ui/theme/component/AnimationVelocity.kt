package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 27.04.2023
 */
val LocalAppAnimationVelocity = staticCompositionLocalOf<AppAnimationVelocity> {
    error("No elevation provided")
}

data class AppAnimationVelocity(
    val searchBarTransform: Int,
    val createNoteFabExpand: Int,
    val fadeIn: Int,
    val fadeOut: Int,
    val common: Int,
    val dialogVisibility: Int,
    val disabled: Int
) {
    enum class Value {
        DISABLE, SLOW, NORMAL, FAST
    }

    companion object {
        private const val DISABLE = 0
        private const val SLOW = 600
        private const val NORMAL = 400
        private const val FAST = 250

        val Disabled = AppAnimationVelocity(
            searchBarTransform = DISABLE,
            createNoteFabExpand = DISABLE,
            fadeIn = DISABLE,
            fadeOut = DISABLE,
            common = DISABLE,
            dialogVisibility = DISABLE,
            disabled = DISABLE
        )
        val Slow = AppAnimationVelocity(
            searchBarTransform = SLOW,
            createNoteFabExpand = 650,
            fadeIn = SLOW,
            fadeOut = SLOW,
            common = SLOW,
            dialogVisibility = 400,
            disabled = DISABLE
        )
        val Normal = AppAnimationVelocity(
            searchBarTransform = NORMAL,
            createNoteFabExpand = 500,
            fadeIn = NORMAL,
            fadeOut = NORMAL,
            common = NORMAL,
            dialogVisibility = 200,
            disabled = DISABLE
        )
        val Fast = AppAnimationVelocity(
            searchBarTransform = FAST,
            createNoteFabExpand = 350,
            fadeIn = FAST,
            fadeOut = FAST,
            common = FAST,
            dialogVisibility = 150,
            disabled = DISABLE
        )
    }
}