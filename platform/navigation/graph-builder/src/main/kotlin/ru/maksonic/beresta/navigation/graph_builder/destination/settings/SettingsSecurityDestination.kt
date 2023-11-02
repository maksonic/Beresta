package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.settings.security.ui.SettingsSecurityScreen

/**
 * @Author maksonic on 03.08.2023
 */
internal fun NavGraphBuilder.settingsSecurityScreen(navigator: AbstractNavigator) {
    composable(route = Destination.Settings.Security.route) { navBackStackEntry ->
        SettingsSecurityScreen(navigator.settingsSecurityRouter(navBackStackEntry))
    }
}