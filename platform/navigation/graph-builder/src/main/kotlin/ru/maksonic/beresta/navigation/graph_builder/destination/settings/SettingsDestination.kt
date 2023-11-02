package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.common.ui_theme.NavigationVelocity
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.settings.ui.SettingsScreen

/**
 * @Author maksonic on 17.02.2023
 */
internal fun NavGraphBuilder.settingsScreen(
    navigator: AbstractNavigator,
    animator: NavigationAnimator,
    velocity: NavigationVelocity
) {
    composable(
        route = Destination.Settings.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route -> animator.slideIntoLeft(this, velocity.slide)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route -> animator.slideOutRight(this, velocity.slide)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        SettingsScreen(navigator.settingsRouter(navBackStackEntry))
    }
}