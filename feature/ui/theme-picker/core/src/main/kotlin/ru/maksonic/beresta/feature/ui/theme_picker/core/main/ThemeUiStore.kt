package ru.maksonic.beresta.feature.ui.theme_picker.core.main

import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ThemeContrast
import ru.maksonic.beresta.common.ui_kit.icons.ThemeLight
import ru.maksonic.beresta.common.ui_kit.icons.ThemeNight
import ru.maksonic.beresta.common.ui_kit.icons.ThemeSystem
import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi
import ru.maksonic.beresta.common.ui_theme.AppThemePaletteUi.*
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.AppThemeUi.*

/**
 * @Author maksonic on 26.02.2023
 */
data class ThemeUi(
    val id: Int,
    val theme: AppThemeUi,
    val title: String = "",
    val icon: ImageVector,
) {
    companion object {
        val Preview = ThemeUi(0, DAY, DAY.name, AppIcon.ThemeLight)
    }

    data class Collection(val data: List<ThemeUi>)

    data class Palette(val id: Int, val palette: AppThemePaletteUi) {
        data class Collection(val filled: List<Palette>, val outlined: List<Palette>)
    }
}

internal object ThemeUiStore {
    private val themeItemsData = listOf(
        ThemeUi(id = SYSTEM.ordinal, SYSTEM, icon = AppIcon.ThemeSystem),
        ThemeUi(id = DAY.ordinal, DAY, icon = AppIcon.ThemeLight),
        ThemeUi(id = NIGHT.ordinal, NIGHT, icon = AppIcon.ThemeNight),
        ThemeUi(id = DARK.ordinal, DARK, icon = AppIcon.ThemeContrast)
    )

    private val filledPaletteData = listOf(
        ThemeUi.Palette(0, BLUE),
        ThemeUi.Palette(1, GREEN),
        ThemeUi.Palette(2, PURPLE),
        ThemeUi.Palette(3, RED),
        ThemeUi.Palette(4, ORANGE),
        ThemeUi.Palette(5, YELLOW),
    )

    private val outlinedPaletteData = listOf(
        ThemeUi.Palette(0, BLACK_OUT),
        ThemeUi.Palette(1, BLUE_OUT),
        ThemeUi.Palette(2, GREEN_OUT),
        ThemeUi.Palette(3, PURPLE_OUT),
        ThemeUi.Palette(4, RED_OUT),
        ThemeUi.Palette(5, ORANGE_OUT),
        ThemeUi.Palette(6, YELLOW_OUT)
    )

    val themes = ThemeUi.Collection(themeItemsData)
    val palettes = ThemeUi.Palette.Collection(filledPaletteData, outlinedPaletteData)
}