package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 24.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.onboardingScreen(api: OnboardingApi.Ui, navigator: AbstractNavigator) {
    composable(route = Destination.Onboarding.route) { navBackStackEntry ->
        val router = navigator.onboardingRouter(navBackStackEntry)
        api.Screen(router)
    }
}