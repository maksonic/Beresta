package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.splash_screen.api.SplashUiApi
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator

/**
 * @Author maksonic on 24.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.splashScreen(api: SplashUiApi, navigator: AppNavigator) {
    composable(route = Destination.Splash.route) { navBackStackEntry ->
        val router = navigator.splashRouter(navBackStackEntry)
        api.Screen(router)
    }
}