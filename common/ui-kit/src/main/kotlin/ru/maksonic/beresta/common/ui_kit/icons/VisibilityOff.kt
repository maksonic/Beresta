package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 02.08.2023
 */
val AppIcon.VisibilityOff: ImageVector
    get() {
        if (_visibilityOff != null) {
            return _visibilityOff!!
        }
        _visibilityOff = ImageVector.Builder(
            name = "VisibilityOff",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveToRelative(645.43F, 527.93F)
            lineToRelative(-68.28F, -68.28F)
            quadToRelative(7.33F, -38.39F, -22.34F, -72.22F)
            reflectiveQuadToRelative(-76.38F, -26.5F)
            lineToRelative(-66.37F, -66.37F)
            quadToRelative(16.28F, -7.28F, 32.95F, -10.92F)
            quadTo(461.67F, 280.0F, 480.0F, 280.0F)
            quadToRelative(75.0F, 0.0F, 127.5F, 52.5F)
            reflectiveQuadTo(660.0F, 460.0F)
            quadToRelative(0.0F, 17.85F, -3.64F, 34.99F)
            quadToRelative(-3.64F, 17.14F, -10.92F, 32.95F)

            moveTo(779.65F, 660.63F)
            lineToRelative(-62.78F, -61.26F)
            quadToRelative(36.33F, -28.52F, 64.75F, -62.19F)
            quadToRelative(28.42F, -33.66F, 49.9F, -77.18F)
            quadToRelative(-50.48F, -100.76F, -143.5F, -160.38F)
            quadTo(595.0F, 240.0F, 480.0F, 240.0F)
            quadToRelative(-29.0F, 0.0F, -55.69F, 3.76F)
            quadToRelative(-26.68F, 3.76F, -52.01F, 11.04F)
            lineToRelative(-68.22F, -68.22F)
            quadToRelative(41.0F, -17.0F, 84.96F, -25.5F)
            quadToRelative(43.96F, -8.5F, 90.96F, -8.5F)
            quadToRelative(143.72F, 0.0F, 259.27F, 75.86F)
            quadToRelative(115.55F, 75.86F, 174.71F, 200.21F)
            quadToRelative(3.48F, 5.72F, 4.6F, 14.17F)
            quadToRelative(1.12F, 8.46F, 1.12F, 17.17F)
            quadToRelative(0.0F, 8.72F, -1.62F, 17.17F)
            quadToRelative(-1.62F, 8.46F, -4.1F, 14.17F)
            quadToRelative(-23.24F, 50.52F, -57.5F, 93.52F)
            quadToRelative(-34.26F, 43.0F, -76.83F, 75.76F)

            moveTo(759.7F, 877.67F)
            lineTo(625.43F, 744.93F)
            quadToRelative(-34.52F, 11.24F, -70.5F, 16.86F)
            quadToRelative(-35.98F, 5.62F, -74.93F, 5.62F)
            quadToRelative(-143.72F, 0.0F, -259.27F, -75.98F)
            quadToRelative(-115.55F, -75.98F, -174.71F, -200.09F)
            quadToRelative(-3.48F, -5.72F, -4.6F, -14.17F)
            quadToRelative(-1.12F, -8.46F, -1.12F, -17.17F)
            quadToRelative(0.0F, -8.72F, 1.12F, -16.79F)
            quadToRelative(1.12F, -8.08F, 4.6F, -13.79F)
            quadToRelative(20.76F, -44.04F, 49.4F, -81.8F)
            quadToRelative(28.64F, -37.76F, 62.92F, -69.76F)
            lineToRelative(-80.37F, -81.37F)
            quadToRelative(-11.72F, -11.72F, -11.72F, -28.82F)
            reflectiveQuadToRelative(12.48F, -29.58F)
            quadToRelative(11.72F, -11.72F, 29.32F, -11.72F)
            reflectiveQuadToRelative(29.32F, 11.72F)
            lineToRelative(680.96F, 680.96F)
            quadToRelative(11.72F, 11.72F, 12.1F, 28.93F)
            quadToRelative(0.38F, 17.22F, -12.1F, 29.7F)
            quadToRelative(-11.72F, 11.72F, -29.32F, 11.72F)
            reflectiveQuadToRelative(-29.32F, -11.72F)

            moveTo(219.85F, 338.63F)
            quadToRelative(-28.52F, 26.48F, -51.21F, 56.04F)
            quadToRelative(-22.68F, 29.57F, -39.92F, 65.33F)
            quadToRelative(50.0F, 101.0F, 143.14F, 160.5F)
            quadTo(365.0F, 680.0F, 480.0F, 680.0F)
            quadToRelative(17.85F, 0.0F, 35.77F, -2.14F)
            quadToRelative(17.92F, -2.14F, 37.92F, -5.14F)
            lineToRelative(-36.0F, -38.0F)
            quadToRelative(-9.81F, 2.76F, -18.85F, 4.02F)
            quadTo(489.8F, 640.0F, 480.0F, 640.0F)
            quadToRelative(-75.0F, 0.0F, -127.5F, -52.5F)
            reflectiveQuadTo(300.0F, 460.0F)
            quadToRelative(0.0F, -9.56F, 1.14F, -18.73F)
            quadToRelative(1.14F, -9.16F, 3.66F, -18.97F)
            lineToRelative(-84.96F, -83.67F)

            moveTo(544.59F, 427.09F)

            moveTo(386.65F, 505.67F)
            close()
        }.build()
        return _visibilityOff!!
    }
private var _visibilityOff: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconVisibilityOffPreview() {
    Image(imageVector = AppIcon.VisibilityOff, contentDescription = null)
}