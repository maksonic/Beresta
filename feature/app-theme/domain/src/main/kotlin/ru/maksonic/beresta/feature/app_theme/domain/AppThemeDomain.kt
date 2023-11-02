package ru.maksonic.beresta.feature.app_theme.domain

import java.io.Serializable
import java.util.Locale

/**
 * @Author maksonic on 12.10.2023
 */
enum class AppThemeDomain {
    SYSTEM,
    DAY,
    NIGHT,
    DARK;

    override fun toString(): String = name.lowercase(Locale.ROOT)
}

enum class AppThemePaletteDomain {
    BLACK_OUT,
    BLUE, BLUE_OUT,
    GREEN, GREEN_OUT,
    PURPLE, PURPLE_OUT,
    RED, RED_OUT,
    ORANGE, ORANGE_OUT,
    YELLOW, YELLOW_OUT
}

data class ThemePaletteContainer(
    val light: AppThemePaletteDomain,
    val night: AppThemePaletteDomain,
    val dark: AppThemePaletteDomain
) : Serializable {
    companion object {
        val Default = ThemePaletteContainer(
            AppThemePaletteDomain.BLUE,
            AppThemePaletteDomain.BLUE_OUT,
            AppThemePaletteDomain.BLUE
        )
    }

    override fun toString(): String = "($light, $night, $dark)"
}

data class ThemeContainer(
    val currentTheme: AppThemeDomain,
    val currentPalette: AppThemePaletteDomain,
    val paletteContainer: ThemePaletteContainer,
    val isDarkMode: Boolean
)