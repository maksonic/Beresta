package ru.maksonic.beresta.feature.splash_screen.ui

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.navigation.router.Destination

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
    private val onboardingVisibility: OnboardingApi.Feature,
    private val themePickerApi: ThemePickerApi.Feature.Theme
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch { fetchDarkModeCurrentState() }
        viewModelScope.launch { setStartScreen() }
    }

    private suspend fun fetchDarkModeCurrentState() {
        themePickerApi.current.collect { theme ->
            _state.update { it.copy(isDarkMode = theme.second) }
        }
    }

    private fun setStartScreen() {
        viewModelScope.launch {
            onboardingVisibility.currentState.collect { isVisible ->
                val destination = if (isVisible) Destination.Onboarding else Destination.Main

                _state.update { splashState ->
                    splashState.copy(route = destination.route, isRunNavigationAction = true)
                }
            }
        }
    }
}