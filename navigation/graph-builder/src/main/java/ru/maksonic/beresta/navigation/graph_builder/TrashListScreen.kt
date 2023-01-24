package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.trash_list.ui.TrashListScreen
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 24.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.trashListScreen(navigator: AppNavigator, defAnimSpeed: Int) {
    composable(
        route = Destination.TrashList.route,
        enterTransition = {
            when (initialState.destination.route) {
                Destination.Main.route ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(defAnimSpeed)
                    )
                else -> null
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                Destination.Main.route ->
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(defAnimSpeed)
                    )
                else -> null
            }
        }
    ) { navBackStackEntry ->
        val router = navigator.trashRouter(navBackStackEntry)
        TrashListScreen(router = router)
    }
}