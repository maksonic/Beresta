package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.settings.appearance.ui.SettingsAppearanceScreen

/**
 * @Author maksonic on 06.07.2023
 */
internal fun NavGraphBuilder.settingsAppearanceScreen(navigator: AbstractNavigator) {
    composable(route = Destination.Settings.Appearance.route) { navBackStackEntry ->
        SettingsAppearanceScreen(navigator.settingsAppearanceRouter(navBackStackEntry))
    }
}