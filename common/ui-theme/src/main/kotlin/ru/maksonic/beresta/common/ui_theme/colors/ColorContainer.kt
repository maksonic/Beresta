package ru.maksonic.beresta.common.ui_theme.colors

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 29.10.2023
 */
data class ColorContainer(
    val categoryId: Int,
    val colorId: Long,
    val value: Color
) {
    companion object {
        val Default = ColorContainer(categoryId = 0, colorId = 0L, Color.Transparent)
    }
}