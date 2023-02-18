package ru.maksonic.beresta.feature.onboarding.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter

/**
 * @Author maksonic on 15.02.2023
 */
interface OnboardingApi {

    interface Visibility {
        suspend fun notShowAgain()
        val currentState: Flow<Boolean>
    }

    interface Ui {
        @Composable
        fun Screen(router: OnboardingRouter)
    }
}