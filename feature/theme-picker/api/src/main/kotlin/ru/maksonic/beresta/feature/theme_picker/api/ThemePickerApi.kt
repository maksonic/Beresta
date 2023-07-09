package ru.maksonic.beresta.feature.theme_picker.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 19.06.2023
 */
interface ThemePickerApi {
    interface Feature {
        interface Theme {
            val current: Flow<Pair<AppTheme, Boolean>>
            suspend fun setTheme(theme: AppTheme)
            suspend fun updateDarkMode(isDarkMode: Boolean)
        }

        interface Palette {
            val current: Flow<PaletteStore>
            suspend fun setPalette(
                currentTheme: AppTheme,
                isDarkMode: Boolean,
                palette: AppThemePalette
            )
        }
    }

    interface Ui {
        @Composable
        fun SheetContent(hideSheet: () -> Unit)
    }
}

