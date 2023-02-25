package ru.maksonic.beresta.feature.theme_selector.api

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.color.ThemeColorPalette

/**
 * @Author maksonic on 20.02.2023
 */
data class ThemeUi(
    val id: Int,
    val theme: AppTheme,
    val title: String = "",
    val icon: ImageVector,
    val isSelected: Boolean = false
)

data class ThemesCollection(val data: Array<ThemeUi>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThemesCollection

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}

data class ColorPalette(
    val palette: ThemeColorPalette,
    val isSelected: Boolean = false,
    val color: Color = Color.Transparent
)