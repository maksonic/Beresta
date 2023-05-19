package ru.maksonic.beresta.feature.splash_screen.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 15.01.2023
 */
data class SplashState(
    val route: String = "",
    val isRunNavigationAction: Boolean = false,
    val isDarkTheme: Boolean = false
)

class SplashViewModel(
    private val onboardingVisibility: OnboardingApi.VisibilityBehavior,
    private val darkThemeChecker: ThemePickerApi.DarkModeChecker
) : ViewModel() {
    val uiState = object : SharedUiState<SplashState>(SplashState()) {}

    init {
        viewModelScope.launch {
            setStartScreen()
        }
        viewModelScope.launch {
            checkDarkTheme()
        }
    }


    private fun setStartScreen() {
        viewModelScope.launch {
            onboardingVisibility.currentState.collect { isVisible ->
                val destination = if (isVisible) Destination.Onboarding else Destination.Main

                uiState.update { splashState ->
                    splashState.copy(route = destination.route, isRunNavigationAction = true)
                }
            }
        }
    }

    private suspend fun checkDarkTheme() {
        darkThemeChecker.isEnabledSystemDarkModeState.collect { isDark ->
            uiState.update { splashState -> splashState.copy(isDarkTheme = isDark)  }
        }
    }
}