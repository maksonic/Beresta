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
val AppIcon.ThemeNight: ImageVector
    get() {
        if (_themeNight != null) {
            return _themeNight!!
        }
        _themeNight = ImageVector.Builder(
            name = "ThemeNight",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.24F, 843.59F)
            quadToRelative(-153.63F, 0.0F, -258.73F, -104.98F)
            quadTo(116.41F, 633.63F, 116.41F, 480.0F)
            quadToRelative(0.0F, -133.93F, 84.74F, -235.43F)
            reflectiveQuadToRelative(223.3F, -123.04F)
            quadToRelative(30.02F, -5.63F, 47.37F, 15.37F)
            reflectiveQuadToRelative(4.98F, 48.31F)
            quadToRelative(-13.89F, 25.04F, -21.32F, 51.65F)
            quadToRelative(-7.42F, 26.61F, -7.42F, 55.5F)
            quadToRelative(0.0F, 91.69F, 64.33F, 155.88F)
            quadToRelative(64.32F, 64.19F, 156.22F, 64.19F)
            quadToRelative(28.37F, 0.0F, 56.48F, -7.45F)
            quadToRelative(28.11F, -7.45F, 50.91F, -20.58F)
            quadToRelative(24.83F, -11.37F, 46.47F, 3.64F)
            quadToRelative(21.64F, 15.01F, 16.01F, 46.55F)
            quadTo(820.17F, 669.0F, 717.63F, 756.29F)
            quadToRelative(-102.54F, 87.29F, -237.39F, 87.29F)

            moveTo(480.24F, 752.59F)
            quadToRelative(81.78F, 0.0F, 147.84F, -43.72F)
            quadToRelative(66.05F, -43.72F, 98.29F, -114.78F)
            quadToRelative(-17.61F, 4.04F, -35.1F, 6.33F)
            quadToRelative(-17.49F, 2.28F, -34.86F, 1.8F)
            quadToRelative(-122.04F, -4.07F, -207.95F, -89.37F)
            quadToRelative(-85.9F, -85.3F, -90.44F, -209.26F)
            quadToRelative(-0.24F, -17.37F, 1.92F, -34.98F)
            quadToRelative(2.16F, -17.61F, 6.45F, -34.98F)
            quadToRelative(-70.83F, 32.48F, -114.78F, 98.65F)
            quadTo(207.65F, 398.46F, 207.65F, 480.0F)
            quadToRelative(0.0F, 112.93F, 79.83F, 192.76F)
            quadToRelative(79.83F, 79.83F, 192.76F, 79.83F)

            moveTo(467.13F, 493.11F)
            close()
        }.build()
        return _themeNight!!
    }
private var _themeNight: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconThemeNightPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.ThemeNight, contentDescription = null)

    }
}