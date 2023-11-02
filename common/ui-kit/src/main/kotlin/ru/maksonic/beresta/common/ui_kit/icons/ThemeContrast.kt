package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 20.02.2023
 */
val AppIcon.ThemeContrast: ImageVector
    get() {
        if (_themeContrast != null) {
            return _themeContrast!!
        }
        _themeContrast = ImageVector.Builder(
            name = "ThemeContrast",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(479.98F, 888.13F)
            quadToRelative(-84.65F, 0.0F, -159.09F, -32.1F)
            reflectiveQuadToRelative(-129.63F, -87.29F)
            quadToRelative(-55.2F, -55.19F, -87.29F, -129.65F)
            quadToRelative(-32.1F, -74.46F, -32.1F, -159.11F)
            quadToRelative(0.0F, -84.65F, 32.1F, -159.09F)
            reflectiveQuadToRelative(87.29F, -129.63F)
            quadToRelative(55.19F, -55.2F, 129.65F, -87.29F)
            quadToRelative(74.46F, -32.1F, 159.11F, -32.1F)
            quadToRelative(84.65F, 0.0F, 159.09F, 32.1F)
            reflectiveQuadToRelative(129.63F, 87.29F)
            quadToRelative(55.2F, 55.19F, 87.29F, 129.65F)
            quadToRelative(32.1F, 74.46F, 32.1F, 159.11F)
            quadToRelative(0.0F, 84.65F, -32.1F, 159.09F)
            reflectiveQuadToRelative(-87.29F, 129.63F)
            quadToRelative(-55.19F, 55.2F, -129.65F, 87.29F)
            quadToRelative(-74.46F, 32.1F, -159.11F, 32.1F)

            moveTo(522.87F, 793.65F)
            quadToRelative(117.09F, -15.96F, 195.67F, -103.9F)
            quadTo(797.13F, 601.8F, 797.13F, 480.0F)
            quadToRelative(0.0F, -121.15F, -78.59F, -209.3F)
            quadToRelative(-78.59F, -88.15F, -195.67F, -104.11F)
            verticalLineToRelative(627.07F)
            close()
        }.build()
        return _themeContrast!!
    }
private var _themeContrast: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconThemeContrastPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.ThemeContrast, contentDescription = null)
    }
}