package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.onboarding.ui.OnboardingScreen
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 24.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.onboardingScreen(navigator: AppNavigator) {
    composable(route = Destination.Onboarding.route) { navBackStackEntry ->
        val router = navigator.onboardingRouter(navBackStackEntry)
        OnboardingScreen(router = router)
    }
}