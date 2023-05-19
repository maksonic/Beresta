package ru.maksonic.beresta.feature.theme_picker.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 24.04.2023
 */
interface ThemePickerApi {

    interface Theme {
        val current: Flow<AppTheme>
        suspend fun setTheme(theme: AppTheme)
    }

    interface Palette {
        val current: Flow<PaletteStore>
        suspend fun setPalette(isDarkTheme: Boolean, palette: AppThemePalette)
    }

    interface DarkModeChecker {
        fun checkSystemDarkTheme(isDarkTheme: Boolean)
        val isEnabledSystemDarkModeState: StateFlow<Boolean>
    }
    interface Ui {
        @Composable
        fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit)
    }
}