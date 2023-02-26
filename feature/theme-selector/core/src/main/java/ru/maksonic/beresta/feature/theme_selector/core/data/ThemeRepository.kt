package ru.maksonic.beresta.feature.theme_selector.core.data

import ru.maksonic.beresta.feature.theme_selector.api.ColorPalette
import ru.maksonic.beresta.feature.theme_selector.api.ThemeUi
import ru.maksonic.beresta.feature.theme_selector.api.ThemesCollection
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette
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

interface ThemeRepository {
    val themes: ThemesCollection
    val palettes: Palettes

    class Core : ThemeRepository {
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
            ColorPalette(0, ThemeColorPalette.BLUE),
            ColorPalette(1, ThemeColorPalette.GREEN),
            ColorPalette(2, ThemeColorPalette.PURPLE),
            ColorPalette(3, ThemeColorPalette.RED),
            ColorPalette(4, ThemeColorPalette.ORANGE),
            ColorPalette(5, ThemeColorPalette.YELLOW),
        )

        private val outlinedPalette = arrayOf(
            ColorPalette(0, ThemeColorPalette.BLACK_OUT),
            ColorPalette(1, ThemeColorPalette.BLUE_OUT),
            ColorPalette(2, ThemeColorPalette.GREEN_OUT),
            ColorPalette(3, ThemeColorPalette.PURPLE_OUT),
            ColorPalette(4, ThemeColorPalette.RED_OUT),
            ColorPalette(5, ThemeColorPalette.ORANGE_OUT),
            ColorPalette(6, ThemeColorPalette.YELLOW_OUT)
        )

        override val themes = ThemesCollection(themesData)
        override val palettes = Palettes(filled = filledPalette, outlined = outlinedPalette)
    }
}