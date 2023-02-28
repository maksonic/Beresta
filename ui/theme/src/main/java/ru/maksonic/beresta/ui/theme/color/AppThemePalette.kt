package ru.maksonic.beresta.ui.theme.color

import java.io.Serializable

/**
 * @Author maksonic on 28.02.2023
 */
enum class AppThemePalette {
    BLACK_OUT,
    BLUE, BLUE_OUT,
    GREEN, GREEN_OUT,
    PURPLE, PURPLE_OUT,
    RED, RED_OUT,
    ORANGE, ORANGE_OUT,
    YELLOW, YELLOW_OUT
}

data class PaletteStore(
    val light: AppThemePalette,
    val dark: AppThemePalette,
) : Serializable {
    companion object {
        val Default = PaletteStore(AppThemePalette.BLUE, AppThemePalette.BLUE_OUT)
    }

    override fun toString(): String = "($light, $dark)"

}