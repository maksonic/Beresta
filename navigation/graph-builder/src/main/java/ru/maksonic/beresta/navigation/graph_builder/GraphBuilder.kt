package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import ru.maksonic.beresta.feature.onboarding.ui.OnboardingScreen
import ru.maksonic.beresta.feature.splash_screen.SplashScreen
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.main.ui.MainScreen

/**
 * @Author maksonic on 15.12.2022
 */
interface GraphBuilder {
    fun buildGraph(
        graphBuilder: NavGraphBuilder,
        systemUiController: SystemUiController,
        startDestination: String
    )

    class Builder : GraphBuilder {
        @OptIn(ExperimentalAnimationApi::class)
        override fun buildGraph(
            graphBuilder: NavGraphBuilder,
            systemUiController: SystemUiController,
            startDestination: String
        ) {
            graphBuilder.navigation(
                route = Destination.route,
                startDestination = startDestination
            ) {
                composable(route = Destination.Splash.route) {
                    SplashScreen()
                }
                composable(route = Destination.Onboarding.route) {
                    OnboardingScreen()
                }
                composable(route = Destination.Main.route) {
                    MainScreen(systemUiController)
                }
            }
        }
    }
}
