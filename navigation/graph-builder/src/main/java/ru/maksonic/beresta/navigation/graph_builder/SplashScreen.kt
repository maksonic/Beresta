package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.splash_screen.SplashScreen
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 24.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.splashScreen(navigator: AppNavigator) {
    composable(route = Destination.Splash.route) { navBackStackEntry ->
        val router = navigator.splashRouter(navBackStackEntry)
        SplashScreen(router = router)
    }
}