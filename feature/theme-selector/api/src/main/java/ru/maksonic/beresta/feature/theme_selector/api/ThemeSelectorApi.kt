package ru.maksonic.beresta.feature.theme_selector.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 15.02.2023
 */
interface ThemeSelectorApi {
    interface Theme {
        suspend fun setTheme(theme: AppTheme)
        val currentTheme: Flow<AppTheme>
    }

    interface Ui {
        @Composable
        fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit, modifier: Modifier)
    }
}