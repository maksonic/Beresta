package ru.maksonic.beresta.feature.language_picker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.language_picker.ui.data.LanguageUi
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 17.02.2023
 */

private val data = LanguageUi.Collection(
    listOf(
        LanguageUi(id = AppLanguage.RUSSIAN.ordinal, AppLanguage.RUSSIAN),
        LanguageUi(id = AppLanguage.ENGLISH.ordinal, AppLanguage.ENGLISH),
        LanguageUi(id = AppLanguage.CHINESE.ordinal, AppLanguage.CHINESE),
        LanguageUi(id = AppLanguage.CHINESE_TR.ordinal, AppLanguage.CHINESE_TR),
    )
)

class LanguagePickerViewModel(private val langEngine: LanguageEngineApi) : ViewModel() {
    private val _languagesCollection = MutableStateFlow(data)
    val languages = _languagesCollection.asStateFlow()

    private val _currentLangSelection = MutableStateFlow(AppLanguage.RUSSIAN)
    val currentLangSelection = _currentLangSelection.asStateFlow()

    init {
        viewModelScope.launch {
            langEngine.current.collect { currentLang ->
                _currentLangSelection.update { currentLang }
            }
        }
    }

    fun setLang(appLanguage: AppLanguage) = viewModelScope.launch {
        _currentLangSelection.update { appLanguage }.let {
            langEngine.setLanguage(appLanguage)
        }
    }
}
