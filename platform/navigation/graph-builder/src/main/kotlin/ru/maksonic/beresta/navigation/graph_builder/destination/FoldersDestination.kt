package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.maksonic.beresta.common.ui_theme.NavigationVelocity
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.folders.ui.FoldersScreen

/**
 * @Author maksonic on 03.04.2023
 */
internal fun NavGraphBuilder.foldersScreen(
    navigator: AbstractNavigator,
    animator: NavigationAnimator,
    velocity: NavigationVelocity
) {
    composable(
        route = Destination.Folders.routeWithArg,
        arguments = listOf(navArgument(Destination.Folders.passedKey) {
            type = NavType.StringType
        }),
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route -> animator.slideIntoUp(this, velocity.slide)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route -> animator.slideOutDown(this, velocity.slide)
                else -> null
            }
        }
    ) { navBackStackEntry ->

        FoldersScreen(navigator.foldersRouter(navBackStackEntry))
    }
}