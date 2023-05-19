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
    val isSelected: Boolean = false
) {

    companion object {
        val Preview = ThemeUiModel(
            0,
            AppTheme.LIGHT,
            title = AppTheme.LIGHT.title,
            icon = AppIcon.ThemeLight,
            isSelected = false
        )
    }

    data class Collection(val data: Array<ThemeUiModel>) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Collection

            if (!data.contentEquals(other.data)) return false

            return true
        }

        override fun hashCode(): Int {
            return data.contentHashCode()
        }
    }

    data class Palette(val id: Int, val palette: AppThemePalette, val isSelected: Boolean = false) {

        data class Collection(val filled: Array<Palette>, val outlined: Array<Palette>) {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Collection

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
    }
}