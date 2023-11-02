package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 19.02.2023
 */
val AppIcon.ThemeLight: ImageVector
    get() {
        if (_themeLight != null) {
            return _themeLight!!
        }
        _themeLight = ImageVector.Builder(
            name = "ThemeLight",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 589.0F)
            quadToRelative(45.46F, 0.0F, 77.23F, -31.77F)
            quadTo(589.0F, 525.46F, 589.0F, 480.0F)
            quadToRelative(0.0F, -45.46F, -31.77F, -77.23F)
            quadTo(525.46F, 371.0F, 480.0F, 371.0F)
            quadToRelative(-45.46F, 0.0F, -77.23F, 31.77F)
            quadTo(371.0F, 434.54F, 371.0F, 480.0F)
            quadToRelative(0.0F, 45.46F, 31.77F, 77.23F)
            quadTo(434.54F, 589.0F, 480.0F, 589.0F)

            moveTo(480.0F, 680.0F)
            quadToRelative(-83.0F, 0.0F, -141.5F, -58.5F)
            reflectiveQuadTo(280.0F, 480.0F)
            quadToRelative(0.0F, -83.0F, 58.5F, -141.5F)
            reflectiveQuadTo(480.0F, 280.0F)
            quadToRelative(83.0F, 0.0F, 141.5F, 58.5F)
            reflectiveQuadTo(680.0F, 480.0F)
            quadToRelative(0.0F, 83.0F, -58.5F, 141.5F)
            reflectiveQuadTo(480.0F, 680.0F)

            moveTo(80.0F, 525.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(34.5F, 480.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(80.0F, 434.5F)
            horizontalLineToRelative(80.0F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(205.5F, 480.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(160.0F, 525.5F)
            lineTo(80.0F, 525.5F)

            moveTo(800.0F, 525.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(754.5F, 480.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(800.0F, 434.5F)
            horizontalLineToRelative(80.0F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(925.5F, 480.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(880.0F, 525.5F)
            horizontalLineToRelative(-80.0F)

            moveTo(480.0F, 205.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(434.5F, 160.0F)
            verticalLineToRelative(-80.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(480.0F, 34.5F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(525.5F, 80.0F)
            verticalLineToRelative(80.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(480.0F, 205.5F)

            moveTo(480.0F, 925.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            quadTo(434.5F, 899.15F, 434.5F, 880.0F)
            verticalLineToRelative(-80.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(480.0F, 754.5F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(525.5F, 800.0F)
            verticalLineToRelative(80.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            quadToRelative(-13.17F, 13.17F, -32.33F, 13.17F)

            moveTo(222.17F, 286.07F)
            lineToRelative(-43.0F, -42.0F)
            quadTo(165.5F, 231.39F, 166.0F, 212.24F)
            reflectiveQuadToRelative(13.17F, -33.06F)
            quadToRelative(13.44F, -13.67F, 32.59F, -13.67F)
            reflectiveQuadToRelative(32.06F, 13.67F)
            lineToRelative(42.24F, 43.0F)
            quadToRelative(12.67F, 13.44F, 12.56F, 31.71F)
            quadToRelative(-0.12F, 18.27F, -12.56F, 31.95F)
            quadToRelative(-12.67F, 13.67F, -31.44F, 13.41F)
            quadToRelative(-18.77F, -0.26F, -32.45F, -13.17F)

            moveTo(716.17F, 780.83F)
            lineTo(673.93F, 737.83F)
            quadToRelative(-12.67F, -13.44F, -12.67F, -32.09F)
            reflectiveQuadToRelative(12.67F, -31.57F)
            quadTo(686.61F, 660.5F, 705.38F, 661.0F)
            quadToRelative(18.77F, 0.5F, 32.45F, 13.17F)
            lineToRelative(43.0F, 41.76F)
            quadTo(794.5F, 728.61F, 794.0F, 747.76F)
            reflectiveQuadToRelative(-13.17F, 33.06F)
            quadTo(767.39F, 794.5F, 748.24F, 794.5F)
            reflectiveQuadToRelative(-32.06F, -13.67F)

            moveTo(674.17F, 286.07F)
            quadTo(660.5F, 273.39F, 661.0F, 254.62F)
            quadToRelative(0.5F, -18.77F, 13.17F, -32.45F)
            lineToRelative(41.76F, -43.0F)
            quadTo(728.61F, 165.5F, 747.76F, 166.0F)
            reflectiveQuadToRelative(33.06F, 13.17F)
            quadToRelative(13.67F, 13.44F, 13.67F, 32.59F)
            reflectiveQuadToRelative(-13.67F, 32.06F)
            lineToRelative(-43.0F, 42.24F)
            quadToRelative(-13.44F, 12.67F, -31.71F, 12.56F)
            quadToRelative(-18.27F, -0.12F, -31.95F, -12.56F)

            moveTo(179.17F, 780.83F)
            quadTo(165.5F, 767.39F, 165.5F, 748.24F)
            reflectiveQuadToRelative(13.67F, -32.06F)
            lineToRelative(43.0F, -42.24F)
            quadToRelative(13.44F, -12.67F, 32.09F, -12.67F)
            reflectiveQuadToRelative(31.57F, 12.67F)
            quadTo(299.5F, 686.61F, 299.0F, 705.38F)
            quadToRelative(-0.5F, 18.77F, -13.17F, 32.45F)
            lineToRelative(-41.76F, 43.0F)
            quadTo(231.39F, 794.5F, 212.24F, 794.0F)
            reflectiveQuadToRelative(-33.06F, -13.17F)

            moveTo(480.0F, 480.0F)
            close()
        }.build()
        return _themeLight!!
    }
private var _themeLight: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSwitchThemePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.ThemeLight, contentDescription = null)

    }
}