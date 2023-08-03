package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.settings.ui.SettingsScreen

/**
 * @Author maksonic on 17.02.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.settingsScreen(
    navigator: AbstractNavigator,
    velocity: Int,
    animator: NavigationAnimator
) {
    composable(
        route = Destination.Settings.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route -> animator.slideIntoLeft(this, velocity)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route -> animator.slideOutRight(this, velocity)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.settingsRouter(navBackStackEntry)
        SettingsScreen(router)
    }
}