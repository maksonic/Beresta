package ru.maksonic.beresta.feature.theme_selector.core.data

import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.feature.theme_selector.api.ThemeUi
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.icons.*

/**
 * @Author maksonic on 26.02.2023
 */
data class Palettes(
    val filled: Array<ColorPalette>,
    val outlined: Array<ColorPalette>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Palettes

        if (!filled.contentEquals(other.filled)) return false
        if (!outlined.contentEquals(other.outlined)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = filled.contentHashCode()
        result = 31 * result + outlined.contentHashCode()
        return result
    }
}

internal object ThemeRepository {
    private val themesData = arrayOf(
        ThemeUi(id = AppTheme.SYSTEM.ordinal, AppTheme.SYSTEM, icon = AppIcon.ThemeSystem),
        ThemeUi(id = AppTheme.LIGHT.ordinal, AppTheme.LIGHT, icon = AppIcon.ThemeLight),
        ThemeUi(id = AppTheme.DARK.ordinal, AppTheme.DARK, icon = AppIcon.ThemeNight),
        ThemeUi(
            id = AppTheme.HIGH_CONTRAST.ordinal,
            AppTheme.HIGH_CONTRAST,
            icon = AppIcon.ThemeContrast
        )
    )

    private val filledPalette = arrayOf(
        ColorPalette(0, AppThemePalette.BLUE),
        ColorPalette(1, AppThemePalette.GREEN),
        ColorPalette(2, AppThemePalette.PURPLE),
        ColorPalette(3, AppThemePalette.RED),
        ColorPalette(4, AppThemePalette.ORANGE),
        ColorPalette(5, AppThemePalette.YELLOW),
    )

    private val outlinedPalette = arrayOf(
        ColorPalette(0, AppThemePalette.BLACK_OUT),
        ColorPalette(1, AppThemePalette.BLUE_OUT),
        ColorPalette(2, AppThemePalette.GREEN_OUT),
        ColorPalette(3, AppThemePalette.PURPLE_OUT),
        ColorPalette(4, AppThemePalette.RED_OUT),
        ColorPalette(5, AppThemePalette.ORANGE_OUT),
        ColorPalette(6, AppThemePalette.YELLOW_OUT)
    )

    val themes = ThemesCollection(themesData)
    val palettes = Palettes(filled = filledPalette, outlined = outlinedPalette)
}