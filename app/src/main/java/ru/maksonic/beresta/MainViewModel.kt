package ru.maksonic.beresta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingVisibility
import ru.maksonic.beresta.feature.theme_selector.ThemeSelector
import ru.maksonic.beresta.navigation.router.AbstractRoute
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 15.12.2022
 */
class MainViewModel(
    private val themeSelector: ThemeSelector,
    private val onboardingVisibility: OnboardingVisibility,
) : ViewModel() {

    data class AppState(
        val theme: MutableStateFlow<AppTheme> = MutableStateFlow(AppTheme.SYSTEM),
        val startScreen: AbstractRoute = Destination.Splash
    )

    private val mutableState = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = mutableState.asStateFlow()

    init {
        applyAppTheme()
        checkShowingOnboarding()
    }

    private fun applyAppTheme() {
        viewModelScope.launch {
            mutableState.update { currentState ->
                currentState.copy(theme = themeSelector.readTheme())
            }
        }
    }

    private fun checkShowingOnboarding() {
        viewModelScope.launch {
            onboardingVisibility.currentState.collect { isShowing ->
                val destination = if (isShowing) Destination.Onboarding else Destination.Main
                mutableState.update { currentState ->
                    currentState.copy(startScreen = destination)
                }
            }
        }
    }
}