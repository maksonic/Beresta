package ru.maksonic.beresta.navigation.graph_builder.destination.trash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.common.ui_theme.NavigationVelocity
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.trash.folders.ui.TrashFoldersScreen

/**
 * @Author maksonic on 31.05.2023
 */
internal fun NavGraphBuilder.trashFoldersScreen(
    navigator: AbstractNavigator,
    animator: NavigationAnimator,
    velocity: NavigationVelocity
) {
    composable(
        route = Destination.Trash.Folders.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Trash.Folders.route -> animator.slideIntoLeft(this, velocity.slide)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Trash.Folders.route -> animator.slideOutRight(this, velocity.slide)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        TrashFoldersScreen(navigator.trashFoldersRouter(navBackStackEntry))
    }
}