package ru.maksonic.beresta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
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
        val theme: AppTheme = AppTheme.SYSTEM,
        val startScreen: AbstractRoute = Destination.Splash
    )

    private val mutableState = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = mutableState.asStateFlow()

    init {
        initAppState()
    }

    private fun initAppState() {
        combine(
            themeSelector.readTheme(),
            onboardingVisibility.currentState
        ) { theme, isOnboarding ->

            val destination = if (isOnboarding) Destination.Onboarding else Destination.Main
            mutableState.update { appState ->
                appState.copy(theme = theme, startScreen = destination)
            }
        }.launchIn(viewModelScope)
    }

}