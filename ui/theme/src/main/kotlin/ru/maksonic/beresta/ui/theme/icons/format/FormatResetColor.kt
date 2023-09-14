package ru.maksonic.beresta.ui.theme.icons.format

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.icons.AppIcon

/**
 * @Author maksonic on 11.09.2023
 */
val AppIcon.FormatResetColor: ImageVector
    get() {
        if (_formatResetColor != null) {
            return _formatResetColor!!
        }
        _formatResetColor = ImageVector.Builder(
            name = "FormatResetColor",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveToRelative(777.65F, 658.63F)
            lineToRelative(-70.85F, -69.33F)
            quadToRelative(5.04F, -15.33F, 7.68F, -30.91F)
            reflectiveQuadToRelative(2.64F, -33.91F)
            quadToRelative(0.0F, -47.0F, -17.14F, -88.28F)
            quadToRelative(-17.14F, -41.28F, -52.14F, -74.28F)
            lineTo(480.0F, 196.07F)
            lineToRelative(-83.22F, 81.46F)
            lineToRelative(-64.13F, -63.89F)
            lineToRelative(99.61F, -98.37F)
            quadToRelative(10.18F, -10.27F, 22.62F, -14.83F)
            quadToRelative(12.44F, -4.56F, 25.12F, -4.56F)
            reflectiveQuadToRelative(25.11F, 4.98F)
            quadToRelative(12.44F, 4.98F, 22.63F, 14.41F)
            lineTo(711.5F, 296.02F)
            quadToRelative(45.44F, 43.43F, 71.03F, 102.37F)
            reflectiveQuadToRelative(25.6F, 126.09F)
            quadToRelative(0.0F, 36.96F, -8.0F, 70.32F)
            quadToRelative(-8.0F, 33.36F, -22.48F, 63.84F)

            moveTo(480.0F, 848.13F)
            quadToRelative(-136.11F, 0.0F, -232.12F, -94.85F)
            reflectiveQuadTo(151.87F, 524.1F)
            quadToRelative(0.0F, -52.3F, 15.88F, -99.54F)
            quadToRelative(15.88F, -47.24F, 48.12F, -91.43F)
            lineTo(74.91F, 192.17F)
            quadToRelative(-11.72F, -11.72F, -11.72F, -29.32F)
            quadToRelative(0.0F, -17.6F, 11.72F, -29.32F)
            quadToRelative(11.72F, -11.72F, 29.32F, -11.72F)
            reflectiveQuadToRelative(29.32F, 11.72F)
            lineToRelative(692.91F, 692.91F)
            quadToRelative(11.72F, 11.72F, 11.72F, 29.19F)
            quadToRelative(0.0F, 17.48F, -11.72F, 29.2F)
            quadToRelative(-11.72F, 11.72F, -29.32F, 11.72F)
            quadToRelative(-17.6F, 0.0F, -29.32F, -11.72F)
            lineToRelative(-97.2F, -97.2F)
            quadToRelative(-41.24F, 29.0F, -88.6F, 44.74F)
            quadToRelative(-47.36F, 15.74F, -102.03F, 15.74F)

            moveTo(480.0F, 757.13F)
            quadToRelative(34.29F, 0.0F, 65.26F, -9.28F)
            quadToRelative(30.96F, -9.28F, 59.24F, -26.33F)
            lineTo(280.48F, 399.5F)
            quadToRelative(-20.04F, 30.57F, -28.83F, 61.24F)
            quadTo(242.87F, 491.41F, 242.87F, 524.0F)
            quadToRelative(0.0F, 97.04F, 69.28F, 165.09F)
            quadTo(381.43F, 757.13F, 480.0F, 757.13F)

            moveTo(440.37F, 558.63F)

            moveTo(554.91F, 436.41F)
            close()
        }.build()
        return _formatResetColor!!
    }
private var _formatResetColor: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconFormatResetColorPreview() {
    Image(imageVector = AppIcon.FormatResetColor, contentDescription = null)
}