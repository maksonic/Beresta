package ru.maksonic.beresta.feature.ui.language_picker.core.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.mapper.AppLangMapper
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi

/**
 * @Author maksonic on 17.02.2023
 */

class LanguagePickerViewModel(
    private val repository: AppLangRepository,
    private val mapper: AppLangMapper<LanguageUi, AppLangUi>
) : ViewModel() {
    private val _languagesCollection = MutableStateFlow(LanguageUi.Collection.Initial)
    val languages = _languagesCollection.asStateFlow()

    private val _currentLangSelection = MutableStateFlow(AppLangUi.RUSSIAN)
    val currentLangSelection = _currentLangSelection.asStateFlow()

    init {
        val languages = mapper.mapListTo(repository.fetchLanguagesList())
        _languagesCollection.value = LanguageUi.Collection(languages)
        viewModelScope.launch {
            repository.fetchAppCurrentLang().collect { currentLang ->
                _currentLangSelection.update { mapper.onlyThemeToUi(currentLang) }
            }
        }
    }

    fun setLang(appLanguage: AppLangUi) = viewModelScope.launch {
        _currentLangSelection.update { appLanguage }.let {
            repository.setLanguage(mapper.onlyThemeToDomain(appLanguage))
        }
    }
}