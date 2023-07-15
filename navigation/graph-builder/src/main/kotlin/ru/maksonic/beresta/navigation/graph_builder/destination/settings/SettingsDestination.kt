package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.settings.ui.SettingsScreen

/**
 * @Author maksonic on 17.02.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.settingsScreen(navigator: AbstractNavigator, animationVelocity: Int) {
    composable(
        route = Destination.Settings.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route ->
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animationVelocity)
                    )
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route ->
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animationVelocity)
                    )
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.settingsRouter(navBackStackEntry)
        SettingsScreen(router)
    }
}