package ru.maksonic.beresta.navigation.graph_builder.destination.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.settings.appearance.ui.SettingsAppearanceScreen

/**
 * @Author maksonic on 06.07.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.settingsAppearanceScreen(
    navigator: AbstractNavigator,
    animationVelocity: Int
) {
    composable(
        route = Destination.Settings.Appearance.route,

    ) { navBackStackEntry ->
        val router = navigator.settingsAppearanceRouter(navBackStackEntry)
        SettingsAppearanceScreen(router)
    }
}