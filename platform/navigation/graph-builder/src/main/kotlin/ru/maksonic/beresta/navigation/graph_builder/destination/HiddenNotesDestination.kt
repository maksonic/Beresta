package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.common.ui_theme.NavigationVelocity
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.hidden_notes.ui.HiddenNotesScreen

/**
 * @Author maksonic on 18.07.2023
 */
internal fun NavGraphBuilder.hiddenNotesScreen(
    navigator: AbstractNavigator,
    animator: NavigationAnimator,
    velocity: NavigationVelocity
) {
    composable(
        route = Destination.HiddenNotes.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route -> animator.slideIntoLeft(this, velocity.slide)
                Destination.Folders.routeWithArg -> animator.slideIntoLeft(this, velocity.slide)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route -> animator.slideOutRight(this, velocity.slide)
                Destination.Folders.routeWithArg -> animator.slideOutRight(this, velocity.slide)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        HiddenNotesScreen(navigator.hiddenNotesRouter(navBackStackEntry))
    }
}