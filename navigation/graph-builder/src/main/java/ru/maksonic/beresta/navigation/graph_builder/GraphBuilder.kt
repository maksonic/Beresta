package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import ru.maksonic.beresta.feature.onboarding.ui.OnboardingScreen
import ru.maksonic.beresta.feature.splash_screen.SplashScreen
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.main.ui.MainScreen
import ru.maksonic.beresta.screen.settings.SettingsScreen

/**
 * @Author maksonic on 15.12.2022
 */
interface GraphBuilder {
    fun buildGraph(graphBuilder: NavGraphBuilder)

    class Builder : GraphBuilder {
        @OptIn(ExperimentalAnimationApi::class)
        override fun buildGraph(graphBuilder: NavGraphBuilder) {
            graphBuilder.navigation(
                route = Destination.route,
                startDestination = Destination.Splash.route
            ) {
                composable(route = Destination.Splash.route) {
                    SplashScreen()
                }
                composable(route = Destination.Onboarding.route) {
                    OnboardingScreen()
                }
                composable(route = Destination.Main.route) {
                    MainScreen()
                }
                composable(route = Destination.Settings.route) {
                    SettingsScreen()
                }
            }
        }
    }
}
