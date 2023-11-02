package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.settings.notifications.ui.SettingsNotificationsScreen

/**
 * @Author maksonic on 15.09.2023
 */
internal fun NavGraphBuilder.settingsNotificationsScreen(navigator: AbstractNavigator) {
    composable(Destination.Settings.Notifications.route) { navBackStackEntry ->
        SettingsNotificationsScreen(navigator.settingsNotificationsRouter(navBackStackEntry))
    }
}