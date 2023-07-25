package ru.maksonic.beresta.navigation.graph_builder.destination.trash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.trash_list.notes.ui.TrashNotesScreen

/**
 * @Author maksonic on 24.02.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.trashNotesScreen(
    navigator: AbstractNavigator,
    velocity: Int,
    animator: NavigationAnimator
) {
    composable(
        route = Destination.TrashNotesList.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route -> animator.slideIntoUp(this, velocity)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route -> animator.slideOutDown(this, velocity)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.trashNotesRouter(navBackStackEntry)
        TrashNotesScreen(router = router)
    }
}