package ru.maksonic.beresta.screen.splash

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibilityFeatureCase
import ru.maksonic.beresta.navigation.router.core.Destination

/**
 * @Author maksonic on 15.01.2023
 */
@Stable
@Immutable
data class SplashState(
    val route: String, val isRunNavigationAction: Boolean, val isDarkMode: Boolean
) {
    companion object {
        val Initial = SplashState(
            route = "",
            isRunNavigationAction = false,
            isDarkMode = false
        )
    }
}

class SplashViewModel(
    private val onboardingVisibilityFeatureCase: OnboardingVisibilityFeatureCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch { setStartScreen() }
    }

    private fun setStartScreen() {
        viewModelScope.launch {
            onboardingVisibilityFeatureCase.visibilityState.collect { isVisible ->
                val destination = if (isVisible) Destination.Onboarding else Destination.Main
                _state.update { splashState ->
                    splashState.copy(route = destination.route, isRunNavigationAction = true)
                }
            }
        }
    }
}