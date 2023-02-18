package ru.maksonic.beresta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.language_selector.api.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 15.12.2022
 */
data class MainState(
    val theme: AppTheme = AppTheme.SYSTEM,
    val language: AppLanguage = AppLanguage.RUSSIAN
)

class MainViewModel(
    private val themeSelector: ThemeSelectorApi,
    private val langSelector: LanguageSelectorApi.Lang
) : ViewModel() {
    private val mutableState = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = mutableState.asStateFlow()

    init {
        readThemeWithLangFromDatastore()
    }

   private fun readThemeWithLangFromDatastore() {
       viewModelScope.launch {
           combine(themeSelector.currentTheme, langSelector.currentLanguage) { theme, language ->
               mutableState.update { state -> state.copy(theme = theme, language = language) }
           }.collect()
       }
   }
}
