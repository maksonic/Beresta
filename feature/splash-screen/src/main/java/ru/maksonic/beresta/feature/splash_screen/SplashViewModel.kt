package ru.maksonic.beresta.feature.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 15.01.2023
 */
data class SplashState(
    val route: String = "",
    val isNavigate: Boolean = false
)

class SplashViewModel(
    private val onboardingVisibility: OnboardingVisibility
) : ViewModel() {
    private val _mutableDestination = MutableStateFlow(SplashState())
    val destination = _mutableDestination.asStateFlow()

    init {
        viewModelScope.launch {
            onboardingVisibility.currentState.collect { isShow ->
                val destination = if (isShow) Destination.Onboarding else Destination.Main
                _mutableDestination.update { splashState ->
                    splashState.copy(route = destination.route, isNavigate = true)
                }
            }
        }
    }
}