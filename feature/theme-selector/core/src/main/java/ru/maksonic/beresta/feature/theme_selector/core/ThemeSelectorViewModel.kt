package ru.maksonic.beresta.feature.theme_selector.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi
import ru.maksonic.beresta.feature.theme_selector.api.ThemeUi
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.ThemeContrast
import ru.maksonic.beresta.ui.theme.icons.ThemeLight
import ru.maksonic.beresta.ui.theme.icons.ThemeNight
import ru.maksonic.beresta.ui.theme.icons.ThemeSystem

/**
 * @Author maksonic on 20.02.2023
 */
class ThemeSelectorViewModel(private val selector: ThemeSelectorApi.Theme) : ViewModel() {

    private val uiData = arrayOf(
        ThemeUi(id = AppTheme.SYSTEM.ordinal, AppTheme.SYSTEM, icon = AppIcon.ThemeSystem),
        ThemeUi(id = AppTheme.LIGHT.ordinal, AppTheme.LIGHT, icon = AppIcon.ThemeLight),
        ThemeUi(id = AppTheme.DARK.ordinal, AppTheme.DARK, icon = AppIcon.ThemeNight),
        ThemeUi(
            id = AppTheme.HIGH_CONTRAST.ordinal,
            AppTheme.HIGH_CONTRAST,
            icon = AppIcon.ThemeContrast
        )
    )

    private val _themesCollection = MutableStateFlow(ThemesCollection(uiData))
    val themes = _themesCollection.asStateFlow()

    init {
        viewModelScope.launch {
            selector.currentTheme.collect { appTheme ->
                selectItem(appTheme.ordinal)
            }
        }
    }

    fun setTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            selectItem(appTheme.ordinal)
            selector.setTheme(appTheme)
        }
    }

    private fun selectItem(ordinal: Int) {
        _themesCollection.update { themesCollection: ThemesCollection ->
            themesCollection.copy(
                data = themesCollection.data.map { item ->
                    val isSelected = item.theme.ordinal == ordinal
                    item.copy(isSelected = isSelected)
                }.toTypedArray()
            )
        }
    }
}
