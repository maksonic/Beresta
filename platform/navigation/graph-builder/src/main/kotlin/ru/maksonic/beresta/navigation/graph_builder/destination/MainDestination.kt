package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.main.ui.screen.MainScreen

/**
 * @Author maksonic on 24.01.2023
 */
internal fun NavGraphBuilder.mainDestination(navigator: AbstractNavigator) {
    composable(route = Destination.Main.route) { navBackStackEntry ->
        MainScreen(navigator.mainRouter(navBackStackEntry))
    }
}