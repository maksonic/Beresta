package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.hidden_notes.ui.HiddenNotesScreen

/**
 * @Author maksonic on 18.07.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.hiddenNotesScreen(
    navigator: AbstractNavigator,
    velocity: Int,
    animator: NavigationAnimator,
) {
    composable(
        route = Destination.HiddenNotes.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route -> animator.slideIntoLeft(this, velocity)
                Destination.Folders.routeWithArg -> animator.slideIntoLeft(this, velocity)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route -> animator.slideOutRight(this, velocity)
                Destination.Folders.routeWithArg -> animator.slideOutRight(this, velocity)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.hiddenNotesRouter(navBackStackEntry)
        HiddenNotesScreen(router)
    }
}