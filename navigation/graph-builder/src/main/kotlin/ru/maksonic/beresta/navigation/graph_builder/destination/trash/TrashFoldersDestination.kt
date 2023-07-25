package ru.maksonic.beresta.navigation.graph_builder.destination.trash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.graph_builder.navigator.NavigationAnimator
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.trash_list.folders.ui.TrashFoldersScreen

/**
 * @Author maksonic on 31.05.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.trashFoldersScreen(
    navigator: AbstractNavigator,
    velocity: Int,
    animator: NavigationAnimator
) {
    composable(
        route = Destination.TrashFoldersList.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.TrashNotesList.route -> animator.slideIntoLeft(this, velocity)
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.TrashNotesList.route -> animator.slideOutRight(this, velocity)
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.trashFoldersRouter(navBackStackEntry)
        TrashFoldersScreen(router = router)
    }
}