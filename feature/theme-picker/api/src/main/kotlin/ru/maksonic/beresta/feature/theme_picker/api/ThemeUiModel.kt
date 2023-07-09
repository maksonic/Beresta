package ru.maksonic.beresta.feature.theme_picker.api

import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ThemeLight

/**
 * @Author maksonic on 24.04.2023
 */
data class ThemeUiModel(
    val id: Int,
    val theme: AppTheme,
    val title: String = "",
    val icon: ImageVector,
) {

    companion object {
        val Preview = ThemeUiModel(
            0,
            AppTheme.LIGHT,
            title = AppTheme.LIGHT.title,
            icon = AppIcon.ThemeLight,
        )
    }

    data class Collection(val data: List<ThemeUiModel>)

    data class Palette(val id: Int, val palette: AppThemePalette) {
        data class Collection(val filled: List<Palette>, val outlined: List<Palette>)
    }
}