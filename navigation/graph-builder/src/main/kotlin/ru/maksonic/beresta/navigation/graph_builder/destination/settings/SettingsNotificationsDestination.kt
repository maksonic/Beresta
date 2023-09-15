package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.settings.notifications.ui.SettingsNotificationsScreen

/**
 * @Author maksonic on 15.09.2023
 */
internal fun NavGraphBuilder.settingsNotificationsScreen(navigator: AbstractNavigator) {
    composable(route = Destination.Settings.Notifications.route) { navBackStackEntry ->
        val router = navigator.settingsNotificationsRouter(navBackStackEntry)
        SettingsNotificationsScreen(router)
    }
}