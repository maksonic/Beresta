package ru.maksonic.beresta.feature.language_selector.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage

/**
 * @Author maksonic on 17.02.2023
 */
data class LanguageUi(val id: Int, val language: AppLanguage, val isSelected: Boolean = false)

@Stable
@Immutable
data class LanguagesCollection(val data: List<LanguageUi>)

class LanguageSelectorViewModel(private val selector: LanguageSelectorApi.Lang) : ViewModel() {

    private val uiData = listOf(
        LanguageUi(id = 0, AppLanguage.RUSSIAN),
        LanguageUi(id = 1, AppLanguage.ENGLISH),
        LanguageUi(id = 2, AppLanguage.CHINES)
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
