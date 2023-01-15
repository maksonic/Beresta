package ru.maksonic.beresta.feature.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.navigation.router.AppNavigator
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 15.01.2023
 */
class SplashViewModel(
    private val navigator: AppNavigator,
    private val isOnboarding: OnboardingVisibility
) : ViewModel() {

    init {
        viewModelScope.launch {
            isOnboarding.currentState.collect { isShow ->
                val destination = if (isShow) Destination.Onboarding else Destination.Main
                navigator.navigate(destination.route)
            }
        }
    }
}