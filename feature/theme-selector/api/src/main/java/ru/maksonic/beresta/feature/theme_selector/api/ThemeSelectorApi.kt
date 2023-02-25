package ru.maksonic.beresta.feature.theme_selector.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette

/**
 * @Author maksonic on 15.02.2023
 */
interface ThemeSelectorApi {
    interface Theme {
        suspend fun setTheme(theme: AppTheme)
        val currentTheme: Flow<AppTheme>

        suspend fun setColorPalette(palette: ThemeColorPalette)
        val currentPalette: Flow<ThemeColorPalette>
    }


    interface Ui {
        @Composable
        fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit)
    }
}