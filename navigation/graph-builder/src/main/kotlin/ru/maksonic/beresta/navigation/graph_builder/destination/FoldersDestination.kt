package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.folders.ui.FoldersScreen

/**
 * @Author maksonic on 03.04.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.foldersScreen(navigator: AbstractNavigator, animationVelocity: Int) {
    composable(
        route = Destination.Folders.routeWithArg,
        arguments = listOf(navArgument(Destination.Folders.passedKey) {
            type = NavType.StringType
        }),
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
        val router = navigator.foldersRouter(navBackStackEntry)
        FoldersScreen(router)
    }
}