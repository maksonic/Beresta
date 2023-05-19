package ru.maksonic.beresta.feature.language_picker.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.language_picker.api.LanguageUi
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 17.02.2023
 */
class LanguagePickerViewModel(private val langEngine: LanguageEngineApi) : ViewModel() {

    private val uiData = listOf(
        LanguageUi(id = AppLanguage.RUSSIAN.ordinal, AppLanguage.RUSSIAN),
        LanguageUi(id = AppLanguage.ENGLISH.ordinal, AppLanguage.ENGLISH),
        LanguageUi(id = AppLanguage.CHINESE.ordinal, AppLanguage.CHINESE),
        LanguageUi(id = AppLanguage.CHINESE_TR.ordinal, AppLanguage.CHINESE_TR),
    )

    private val _languagesCollection = MutableStateFlow(LanguageUi.Collection(uiData))
    val languages = _languagesCollection.asStateFlow()

    init {
        viewModelScope.launch {
            langEngine.current.collect { appLanguage ->
                updateLanguageSelectionState(appLanguage.ordinal)
            }
        }
    }

    fun setLang(appLanguage: AppLanguage) {
        viewModelScope.launch {
            updateLanguageSelectionState(appLanguage.ordinal)
            langEngine.setLanguage(appLanguage)
        }
    }

    private fun updateLanguageSelectionState(ordinal: Int) {
        _languagesCollection.update { languagesCollection: LanguageUi.Collection ->
            languagesCollection.copy(data = languagesCollection.data.map { item ->
                val isSelected = item.language.ordinal == ordinal
                item.copy(isSelected = isSelected)
            })
        }
    }
}
