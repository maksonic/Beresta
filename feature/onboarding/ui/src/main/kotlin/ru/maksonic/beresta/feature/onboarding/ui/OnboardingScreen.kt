package ru.maksonic.beresta.feature.onboarding.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter

/**
 * @Author maksonic on 19.06.2023
 */
class OnboardingScreen : OnboardingApi.Ui {

    @Composable
    override fun Screen(router: OnboardingRouter) {
        Container(router)
    }
}
