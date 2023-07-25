package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 15.07.2023
 */
val AppIcon.Speed: ImageVector
    get() {
        if (_speed != null) {
            return _speed!!
        }
        _speed = ImageVector.Builder(
            name = "Speed",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(418.0F, 620.0F)
            quadToRelative(23.89F, 24.0F, 61.7F, 23.5F)
            quadTo(517.52F, 643.0F, 536.0F, 616.0F)
            lineToRelative(164.93F, -245.11F)
            quadToRelative(10.44F, -15.91F, -2.74F, -29.09F)
            reflectiveQuadToRelative(-29.09F, -2.74F)
            lineTo(424.0F, 504.0F)
            quadToRelative(-27.0F, 18.48F, -28.5F, 55.3F)
            quadTo(394.0F, 596.12F, 418.0F, 620.0F)

            moveTo(480.0F, 151.87F)
            quadToRelative(34.8F, 0.0F, 68.97F, 5.64F)
            quadToRelative(34.16F, 5.64F, 66.68F, 17.68F)
            quadToRelative(18.15F, 6.24F, 34.0F, 24.65F)
            quadToRelative(15.85F, 18.41F, 6.89F, 35.33F)
            quadToRelative(-8.96F, 16.91F, -36.48F, 22.99F)
            quadToRelative(-27.52F, 6.08F, -46.67F, 0.08F)
            quadToRelative(-22.85F, -7.8F, -46.08F, -11.59F)
            quadToRelative(-23.23F, -3.78F, -47.31F, -3.78F)
            quadToRelative(-131.33F, 0.0F, -224.23F, 92.66F)
            quadTo(162.87F, 428.19F, 162.87F, 560.0F)
            quadToRelative(0.0F, 42.0F, 11.26F, 81.68F)
            quadToRelative(11.26F, 39.69F, 31.78F, 75.44F)
            horizontalLineToRelative(548.17F)
            quadToRelative(23.0F, -38.0F, 33.02F, -77.57F)
            quadTo(797.13F, 600.0F, 797.13F, 556.0F)
            quadToRelative(0.0F, -23.71F, -3.78F, -46.51F)
            quadToRelative(-3.78F, -22.8F, -11.59F, -45.12F)
            quadToRelative(-6.0F, -19.39F, -1.16F, -37.9F)
            quadToRelative(4.84F, -18.51F, 20.94F, -30.76F)
            quadToRelative(14.96F, -11.14F, 32.61F, -6.66F)
            quadToRelative(17.65F, 4.48F, 24.13F, 20.63F)
            quadToRelative(14.07F, 34.69F, 21.58F, 70.87F)
            quadToRelative(7.51F, 36.18F, 8.27F, 73.46F)
            quadToRelative(0.76F, 57.72F, -13.48F, 110.87F)
            quadToRelative(-14.24F, 53.15F, -41.71F, 101.19F)
            quadToRelative(-12.44F, 20.15F, -33.21F, 31.11F)
            quadToRelative(-20.77F, 10.96F, -43.73F, 10.96F)
            lineTo(204.0F, 808.13F)
            quadToRelative(-22.91F, 0.0F, -44.06F, -11.2F)
            quadToRelative(-21.15F, -11.2F, -32.87F, -30.87F)
            quadToRelative(-26.24F, -45.72F, -40.72F, -97.41F)
            quadToRelative(-14.48F, -51.69F, -14.48F, -108.66F)
            quadToRelative(0.0F, -84.43F, 32.12F, -158.46F)
            quadToRelative(32.12F, -74.03F, 87.68F, -129.68F)
            quadToRelative(55.56F, -55.65F, 129.99F, -87.82F)
            quadToRelative(74.43F, -32.17F, 158.34F, -32.17F)

            moveTo(491.54F, 468.46F)
            close()
        }.build()
        return _speed!!
    }
private var _speed: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSpeedPreview() {
    Image(imageVector = AppIcon.Speed, contentDescription = null)
}