package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 19.02.2023
 */
val AppIcon.ThemeLight: ImageVector
    get() {
        if (_themeLight != null) {
            return _themeLight!!
        }
        _themeLight = ImageVector.Builder(
            name = "SwithTheme",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(684.41F, 602.76F)
            quadToRelative(-17.39F, -6.48F, -25.11F, -23.41F)
            quadToRelative(-7.72F, -16.93F, -1.24F, -35.76F)
            quadToRelative(6.04F, -15.09F, 8.56F, -31.43F)
            quadToRelative(2.52F, -16.35F, 2.52F, -37.96F)
            quadToRelative(0.0F, -72.37F, -54.61F, -128.24F)
            quadToRelative(-54.61F, -55.87F, -129.74F, -55.87F)
            quadToRelative(-17.37F, 0.0F, -34.6F, 2.4F)
            quadToRelative(-17.23F, 2.4F, -33.84F, 9.21F)
            quadToRelative(-19.11F, 7.0F, -35.31F, -0.48F)
            quadToRelative(-16.19F, -7.48F, -23.15F, -25.63F)
            quadToRelative(-6.72F, -18.15F, 0.38F, -35.42F)
            quadToRelative(7.1F, -17.27F, 25.25F, -23.75F)
            quadToRelative(24.33F, -9.04F, 48.89F, -13.19F)
            quadToRelative(24.57F, -4.14F, 52.37F, -4.14F)
            quadToRelative(114.43F, 0.0F, 194.89F, 80.84F)
            quadToRelative(80.46F, 80.84F, 80.46F, 194.27F)
            quadToRelative(0.0F, 29.85F, -3.26F, 54.01F)
            quadToRelative(-3.26F, 24.16F, -12.78F, 49.21F)
            quadToRelative(-6.48F, 17.15F, -24.13F, 24.11F)
            quadToRelative(-17.65F, 6.96F, -35.56F, 1.24F)

            moveTo(480.0F, 761.91F)
            quadToRelative(-117.48F, 0.0F, -199.7F, -82.1F)
            quadTo(198.09F, 597.72F, 198.09F, 480.0F)
            quadToRelative(0.0F, -40.52F, 12.02F, -81.07F)
            quadToRelative(12.02F, -40.54F, 36.59F, -76.35F)
            quadToRelative(5.72F, -8.96F, 15.53F, -14.17F)
            quadToRelative(9.81F, -5.22F, 20.01F, -5.22F)
            quadToRelative(12.44F, -0.76F, 24.13F, 5.96F)
            quadToRelative(11.69F, 6.72F, 17.41F, 17.91F)
            quadToRelative(52.41F, 95.54F, 133.41F, 176.76F)
            quadToRelative(81.0F, 81.22F, 176.78F, 132.11F)
            quadToRelative(10.2F, 5.72F, 16.53F, 15.41F)
            quadToRelative(6.34F, 9.69F, 7.34F, 22.13F)
            quadToRelative(1.0F, 11.44F, -4.36F, 21.75F)
            quadToRelative(-5.36F, 10.32F, -14.79F, 16.79F)
            quadToRelative(-35.33F, 24.09F, -75.63F, 36.99F)
            quadToRelative(-40.3F, 12.9F, -83.07F, 12.9F)

            moveTo(480.0F, 674.5F)
            quadToRelative(11.37F, 0.0F, 22.36F, -1.26F)
            quadToRelative(10.99F, -1.26F, 23.36F, -5.3F)
            quadToRelative(-69.98F, -42.26F, -130.18F, -102.23F)
            quadToRelative(-60.21F, -59.97F, -103.47F, -129.9F)
            quadToRelative(-3.28F, 11.37F, -4.92F, 21.98F)
            quadTo(285.5F, 468.39F, 285.5F, 480.0F)
            quadToRelative(0.0F, 82.61F, 57.83F, 138.17F)
            quadTo(401.15F, 673.74F, 480.0F, 674.5F)

            moveTo(405.61F, 555.15F)

            moveTo(480.0F, 125.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(434.5F, 80.0F)
            verticalLineToRelative(-40.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(480.0F, -5.5F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(525.5F, 40.0F)
            verticalLineToRelative(40.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(480.0F, 125.5F)

            moveTo(480.0F, 965.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            quadTo(434.5F, 939.15F, 434.5F, 920.0F)
            verticalLineToRelative(-40.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(480.0F, 834.5F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(525.5F, 880.0F)
            verticalLineToRelative(40.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            quadToRelative(-13.17F, 13.17F, -32.33F, 13.17F)

            moveTo(730.17F, 228.83F)
            quadTo(716.5F, 215.15F, 716.5F, 196.5F)
            reflectiveQuadToRelative(13.67F, -32.33F)
            lineToRelative(28.0F, -28.0F)
            quadTo(771.85F, 122.5F, 791.0F, 122.5F)
            reflectiveQuadToRelative(32.83F, 13.67F)
            quadTo(837.5F, 149.85F, 837.0F, 168.5F)
            reflectiveQuadToRelative(-14.17F, 32.33F)
            lineToRelative(-28.0F, 28.0F)
            quadTo(781.15F, 242.5F, 762.5F, 242.5F)
            reflectiveQuadToRelative(-32.33F, -13.67F)

            moveTo(137.17F, 822.83F)
            quadTo(123.5F, 809.15F, 123.5F, 790.5F)
            reflectiveQuadToRelative(13.67F, -32.33F)
            lineToRelative(28.0F, -28.0F)
            quadTo(177.85F, 717.5F, 195.88F, 717.5F)
            quadToRelative(18.03F, 0.0F, 31.71F, 12.44F)
            quadToRelative(13.67F, 12.67F, 14.79F, 31.94F)
            quadToRelative(1.12F, 19.27F, -12.56F, 32.95F)
            lineToRelative(-28.0F, 28.0F)
            quadTo(188.15F, 836.5F, 169.5F, 836.5F)
            reflectiveQuadToRelative(-32.33F, -13.67F)

            moveTo(880.0F, 525.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(834.5F, 480.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(880.0F, 434.5F)
            horizontalLineToRelative(40.0F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(965.5F, 480.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(920.0F, 525.5F)
            horizontalLineToRelative(-40.0F)

            moveTo(40.0F, 525.5F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadTo(-5.5F, 480.0F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadTo(40.0F, 434.5F)
            horizontalLineToRelative(40.0F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadTo(125.5F, 480.0F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadTo(80.0F, 525.5F)
            lineTo(40.0F, 525.5F)

            moveTo(758.17F, 822.83F)
            lineTo(730.17F, 794.83F)
            quadTo(717.5F, 782.15F, 717.5F, 764.0F)
            reflectiveQuadToRelative(12.44F, -31.59F)
            quadTo(742.37F, 718.5F, 761.76F, 717.5F)
            reflectiveQuadToRelative(33.06F, 12.67F)
            lineToRelative(28.0F, 28.0F)
            quadToRelative(13.44F, 13.44F, 13.06F, 31.71F)
            quadToRelative(-0.38F, 18.27F, -12.82F, 31.95F)
            quadTo(810.39F, 835.5F, 791.0F, 835.88F)
            quadToRelative(-19.39F, 0.38F, -32.83F, -13.06F)

            moveTo(165.17F, 228.83F)
            lineTo(137.17F, 200.83F)
            quadToRelative(-13.44F, -13.44F, -13.06F, -31.21F)
            quadToRelative(0.38F, -17.77F, 12.82F, -31.45F)
            quadTo(149.37F, 124.5F, 168.76F, 123.5F)
            reflectiveQuadToRelative(33.06F, 12.67F)
            lineToRelative(28.0F, 28.0F)
            quadTo(243.5F, 177.85F, 243.5F, 196.5F)
            reflectiveQuadToRelative(-13.67F, 32.33F)
            quadTo(216.15F, 242.5F, 197.5F, 242.5F)
            reflectiveQuadToRelative(-32.33F, -13.67F)
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