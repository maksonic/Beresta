package ru.maksonic.beresta.navigation.graph_builder.destination.trash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.common.ui_theme.NavigationVelocity
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.trash.notes.ui.TrashNotesScreen

/**
 * @Author maksonic on 24.02.2023
 */
internal fun NavGraphBuilder.trashNotesScreen(
    navigator: AbstractNavigator,
    animator: NavigationAnimator,
    velocity: NavigationVelocity
) {
    composable(
        route = Destination.Trash.Notes.route,
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
        TrashNotesScreen(navigator.trashNotesRouter(navBackStackEntry))
    }
}