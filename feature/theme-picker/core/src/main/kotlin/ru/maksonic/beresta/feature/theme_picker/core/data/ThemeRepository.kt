package ru.maksonic.beresta.feature.theme_picker.core.data

import ru.maksonic.beresta.feature.theme_picker.api.ThemeUiModel
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.AppThemePalette
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ThemeContrast
import ru.maksonic.beresta.ui.theme.icons.ThemeLight
import ru.maksonic.beresta.ui.theme.icons.ThemeNight
import ru.maksonic.beresta.ui.theme.icons.ThemeSystem

/**
 * @Author maksonic on 26.02.2023
 */
internal object ThemeRepository {
    private val themesData = arrayOf(
        ThemeUiModel(id = AppTheme.SYSTEM.ordinal, AppTheme.SYSTEM, icon = AppIcon.ThemeSystem),
        ThemeUiModel(id = AppTheme.LIGHT.ordinal, AppTheme.LIGHT, icon = AppIcon.ThemeLight),
        ThemeUiModel(id = AppTheme.DARK.ordinal, AppTheme.DARK, icon = AppIcon.ThemeNight),
        ThemeUiModel(
            id = AppTheme.HIGH_CONTRAST.ordinal,
            AppTheme.HIGH_CONTRAST,
            icon = AppIcon.ThemeContrast
        )
    )

    private val filledPalette = arrayOf(
        ThemeUiModel.Palette(0, AppThemePalette.BLUE),
        ThemeUiModel.Palette(1, AppThemePalette.GREEN),
        ThemeUiModel.Palette(2, AppThemePalette.PURPLE),
        ThemeUiModel.Palette(3, AppThemePalette.RED),
        ThemeUiModel.Palette(4, AppThemePalette.ORANGE),
        ThemeUiModel.Palette(5, AppThemePalette.YELLOW),
    )

    private val outlinedPalette = arrayOf(
        ThemeUiModel.Palette(0, AppThemePalette.BLACK_OUT),
        ThemeUiModel.Palette(1, AppThemePalette.BLUE_OUT),
        ThemeUiModel.Palette(2, AppThemePalette.GREEN_OUT),
        ThemeUiModel.Palette(3, AppThemePalette.PURPLE_OUT),
        ThemeUiModel.Palette(4, AppThemePalette.RED_OUT),
        ThemeUiModel.Palette(5, AppThemePalette.ORANGE_OUT),
        ThemeUiModel.Palette(6, AppThemePalette.YELLOW_OUT)
    )

    val themes = ThemeUiModel.Collection(themesData)
    val palettes =
        ThemeUiModel.Palette.Collection(filled = filledPalette, outlined = outlinedPalette)
}