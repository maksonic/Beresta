package ru.maksonic.beresta.common.ui_theme

import java.io.Serializable

/**
 * @Author maksonic on 14.10.2023
 */
data class ThemePalettesUiContainer(
    val light: AppThemePaletteUi,
    val night: AppThemePaletteUi,
    val dark: AppThemePaletteUi
) : Serializable {
    companion object {
        val Default = ThemePalettesUiContainer(
            AppThemePaletteUi.BLUE,
            AppThemePaletteUi.BLUE_OUT,
            AppThemePaletteUi.BLUE
        )
    }

    override fun toString(): String = "($light, $night, $dark)"
}