package ru.maksonic.beresta.feature.language_selector.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.LanguageUi
import ru.maksonic.beresta.feature.language_selector.api.LanguagesCollection
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageSelectorViewModel(private val selector: LanguageSelectorApi.Lang) : ViewModel() {

    private val uiData = listOf(
        LanguageUi(id = AppLanguage.RUSSIAN.ordinal, AppLanguage.RUSSIAN),
        LanguageUi(id = AppLanguage.ENGLISH.ordinal, AppLanguage.ENGLISH),
        LanguageUi(id = AppLanguage.CHINESE.ordinal, AppLanguage.CHINESE),
        LanguageUi(id = AppLanguage.CHINESE_TR.ordinal, AppLanguage.CHINESE_TR),
    )

    private val _languagesCollection = MutableStateFlow(LanguagesCollection(uiData))
    val languages = _languagesCollection.asStateFlow()

    init {
        viewModelScope.launch {
            selector.currentLanguage.collect { appLanguage ->
                selectItem(appLanguage.ordinal)
            }
        }
    }


    fun setLang(appLanguage: AppLanguage) {
        viewModelScope.launch {
            selectItem(appLanguage.ordinal)
            selector.setLanguage(appLanguage)
        }
    }

    private fun selectItem(ordinal: Int) {
        _languagesCollection.update { languagesCollection: LanguagesCollection ->
            languagesCollection.copy(data = languagesCollection.data.map { item ->
                val isSelected = item.language.ordinal == ordinal
                item.copy(isSelected = isSelected)
            })
        }
    }
}
