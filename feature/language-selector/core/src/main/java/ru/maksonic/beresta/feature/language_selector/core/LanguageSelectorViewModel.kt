package ru.maksonic.beresta.feature.language_selector.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.language_selector.api.AppLanguage
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi

/**
 * @Author maksonic on 17.02.2023
 */
class LanguageSelectorViewModel(private val selector: LanguageSelectorApi.Lang) : ViewModel() {

    fun setLang(lang: AppLanguage) {
        viewModelScope.launch {
            selector.setLanguage(lang)
        }
    }
}