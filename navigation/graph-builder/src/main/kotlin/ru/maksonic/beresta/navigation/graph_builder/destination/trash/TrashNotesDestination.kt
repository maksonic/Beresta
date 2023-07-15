package ru.maksonic.beresta.navigation.graph_builder.destination.trash

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.trash_list.notes.ui.TrashNotesScreen

/**
 * @Author maksonic on 24.02.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.trashNotesScreen(navigator: AbstractNavigator, animationVelocity: Int) {
    composable(
        route = Destination.TrashNotesList.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route ->
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(animationVelocity)
                    )
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route ->
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(animationVelocity)
                    )
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.trashNotesRouter(navBackStackEntry)
        TrashNotesScreen(router = router)
    }
}