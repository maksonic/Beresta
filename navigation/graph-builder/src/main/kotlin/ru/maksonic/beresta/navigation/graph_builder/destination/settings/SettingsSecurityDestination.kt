package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.settings.security.ui.SettingsSecurityScreen

/**
 * @Author maksonic on 03.08.2023
 */
internal fun NavGraphBuilder.settingsSecurityScreen(
    navigator: AbstractNavigator,
    animationVelocity: Int
) {
    composable(route = Destination.Settings.Security.route) { navBackStackEntry ->
        val router = navigator.settingsSecurityRouter(navBackStackEntry)
        SettingsSecurityScreen(router)
    }
}