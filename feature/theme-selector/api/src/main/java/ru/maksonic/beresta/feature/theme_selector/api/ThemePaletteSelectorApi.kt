package ru.maksonic.beresta.feature.theme_selector.api

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.color.PaletteStore

/**
 * @Author maksonic on 28.02.2023
 */
interface ThemePaletteSelectorApi {
    val currentPalette: Flow<PaletteStore>
    suspend fun setPalette(isDarkTheme: Boolean, palette: AppThemePalette)
}