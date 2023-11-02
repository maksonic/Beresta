package ru.maksonic.beresta.feature.onboarding.ui.api

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.navigation.router.routes.OnboardingRouter

/**
 * @Author maksonic on 12.10.2023
 */
interface OnboardingUiApi {
    @Composable
    fun Screen(router: OnboardingRouter)
}