package ru.maksonic.beresta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.theme_selector.ThemeSelector
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 15.12.2022
 */
class MainViewModel(private val themeSelector: ThemeSelector) : ViewModel() {
    private val mutableState = MutableStateFlow(AppTheme.SYSTEM)
    val theme: StateFlow<AppTheme> = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            themeSelector.currentSavedTheme.collect { savedThemeFromDatastore ->
                mutableState.update { savedThemeFromDatastore }
            }
        }
    }
}