package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import ru.maksonic.beresta.feature.onboarding.ui.OnboardingScreen
import ru.maksonic.beresta.feature.splash_screen.SplashScreen
import ru.maksonic.beresta.feature.trash_list.ui.TrashListScreen
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.screen.main.ui.MainScreen
import ru.maksonic.beresta.screen.settings.SettingsScreen

/**
 * @Author maksonic on 15.12.2022
 */
interface GraphBuilder {
    fun buildGraph(graphBuilder: NavGraphBuilder)

    class Builder(private val navigator: AppNavigator) : GraphBuilder {
        private companion object {
            private const val DEF_ANIM_SPEED = 300
        }

        @OptIn(ExperimentalAnimationApi::class)
        override fun buildGraph(graphBuilder: NavGraphBuilder) {
            graphBuilder.navigation(
                route = Destination.route,
                startDestination = Destination.Splash.route,
                enterTransition = { fadeIn(animationSpec = tween(DEF_ANIM_SPEED)) },
                exitTransition = { fadeOut(animationSpec = tween(DEF_ANIM_SPEED)) }
            ) {
                composable(route = Destination.Splash.route) { navBackStackEntry ->
                    val router = navigator.splashRouter(navBackStackEntry)
                    SplashScreen(router = router)
                }
                composable(route = Destination.Onboarding.route) { navBackStackEntry ->
                    val router = navigator.onboardingRouter(navBackStackEntry)
                    OnboardingScreen(router = router)
                }
                composable(route = Destination.Main.route) { navBackStackEntry ->
                    val router = navigator.mainRouter(navBackStackEntry)
                    MainScreen(router = router)
                }
                composable(
                    route = Destination.Settings.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Destination.Main.route ->
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(DEF_ANIM_SPEED)
                                )
                            else -> null
                        }
                    },
                    popExitTransition = {
                        when (targetState.destination.route) {
                            Destination.Main.route ->
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(DEF_ANIM_SPEED)
                                )
                            else -> null
                        }
                    }
                ) { navBackStackEntry ->
                    val router = navigator.settingsRouter(navBackStackEntry)
                    SettingsScreen(router = router)
                }
                composable(
                    route = Destination.TrashList.route,
                    enterTransition = {
                        when (initialState.destination.route) {
                            Destination.Main.route ->
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Up,
                                    animationSpec = tween(DEF_ANIM_SPEED)
                                )
                            else -> null
                        }
                    },
                    popExitTransition = {
                        when (targetState.destination.route) {
                            Destination.Main.route ->
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Down,
                                    animationSpec = tween(DEF_ANIM_SPEED)
                                )
                            else -> null
                        }
                    }
                ) { navBackStackEntry ->
                    val router = navigator.trashRouter(navBackStackEntry)
                    TrashListScreen(router = router)
                }
            }
        }
    }
}
