package ru.maksonic.beresta.feature.onboarding.ui.core.screen

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.onboarding.ui.api.OnboardingUiApi
import ru.maksonic.beresta.navigation.router.routes.OnboardingRouter

/**
 * @Author maksonic on 12.10.2023
 */
class OnboardingUiCore : OnboardingUiApi {

    @Composable
    override fun Screen(router: OnboardingRouter) {
        Container(router)
    }
}