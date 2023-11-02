package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.maksonic.beresta.feature.onboarding.ui.api.OnboardingUiApi
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination

/**
 * @Author maksonic on 24.01.2023
 */
internal fun NavGraphBuilder.onboardingDestination(
    api: OnboardingUiApi,
    navigator: AbstractNavigator
) {
    composable(route = Destination.Onboarding.route) { navBackStackEntry ->
        api.Screen(navigator.onboardingRouter(navBackStackEntry))
    }
}