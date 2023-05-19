package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.screen.main.ui.MainScreen

/**
 * @Author maksonic on 24.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.mainScreen(navigator: AppNavigator) {
    composable(route = Destination.Main.route) { navBackStackEntry ->
        val router = navigator.mainRouter(navBackStackEntry)
        MainScreen(router = router)
    }
}