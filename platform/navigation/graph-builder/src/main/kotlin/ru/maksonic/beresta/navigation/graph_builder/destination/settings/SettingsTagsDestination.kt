package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination
import ru.maksonic.beresta.screen.settings.tags.ui.SettingsTagsScreen

/**
 * @Author maksonic on 11.11.2023
 */
internal fun NavGraphBuilder.settingsTagsScreen(navigator: AbstractNavigator) {
    composable(route = Destination.Settings.Tags.route) { navBackStackEntry ->
        SettingsTagsScreen(navigator.settingsTagsRouter(navBackStackEntry))
    }
}