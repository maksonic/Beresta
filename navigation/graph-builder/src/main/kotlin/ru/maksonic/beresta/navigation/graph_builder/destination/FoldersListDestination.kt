package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersListApi
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator

/**
 * @Author maksonic on 03.04.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.foldersListScreen(
    api: FoldersListApi.Ui,
    navigator: AppNavigator,
    defAnimSpeed: Int
) {
    composable(
        route = Destination.NotesFoldersList.routeWithListArgs,
        arguments = listOf(
            navArgument(Destination.NotesFoldersList.passedKeysList.first()) {
                type = NavType.BoolType
            },
            navArgument(Destination.NotesFoldersList.passedKeysList.last()) {
                type = NavType.LongType
                defaultValue = -1
            },
        ),
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
        val router = navigator.foldersRouter(navBackStackEntry)
        api.Screen(router = router)
    }
}