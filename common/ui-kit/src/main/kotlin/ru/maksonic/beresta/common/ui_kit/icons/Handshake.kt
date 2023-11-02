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
val AppIcon.Handshake: ImageVector
    get() {
        if (_handshake != null) {
            return _handshake!!
        }
        _handshake = ImageVector.Builder(
            name = "Handshake",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(474.28F, 800.0F)
            quadToRelative(4.0F, 0.0F, 7.88F, -1.76F)
            reflectiveQuadToRelative(6.36F, -4.24F)
            lineToRelative(326.8F, -326.8F)
            quadToRelative(12.72F, -12.72F, 18.82F, -28.91F)
            quadToRelative(6.1F, -16.2F, 6.1F, -32.87F)
            quadToRelative(0.0F, -17.43F, -6.1F, -33.37F)
            quadToRelative(-6.1F, -15.93F, -18.82F, -27.89F)
            lineTo(654.17F, 183.0F)
            quadToRelative(-11.48F, -12.24F, -26.34F, -17.62F)
            quadTo(612.98F, 160.0F, 596.5F, 160.0F)
            quadToRelative(-15.48F, 0.0F, -30.84F, 5.38F)
            quadToRelative(-15.36F, 5.38F, -27.12F, 16.66F)
            lineToRelative(-8.85F, 9.09F)
            lineToRelative(72.09F, 72.85F)
            quadToRelative(16.19F, 15.44F, 23.79F, 34.75F)
            quadToRelative(7.6F, 19.32F, 7.6F, 41.23F)
            quadToRelative(0.0F, 44.87F, -30.65F, 75.52F)
            quadTo(571.87F, 446.13F, 527.0F, 446.13F)
            quadToRelative(-21.67F, 0.0F, -41.49F, -7.6F)
            quadToRelative(-19.82F, -7.6F, -35.25F, -23.03F)
            lineToRelative(-74.28F, -73.52F)
            lineToRelative(-169.5F, 169.26F)
            quadToRelative(-3.0F, 3.0F, -4.5F, 6.62F)
            quadToRelative(-1.5F, 3.62F, -1.5F, 7.62F)
            quadToRelative(0.0F, 8.0F, 5.88F, 14.26F)
            quadToRelative(5.88F, 6.26F, 13.88F, 6.26F)
            quadToRelative(4.0F, 0.0F, 8.12F, -2.0F)
            quadToRelative(4.12F, -2.0F, 6.12F, -4.0F)
            lineTo(367.37F, 407.35F)
            lineToRelative(56.0F, 56.0F)
            lineTo(291.48F, 596.0F)
            quadToRelative(-3.0F, 3.0F, -4.5F, 6.74F)
            reflectiveQuadToRelative(-1.5F, 7.74F)
            quadToRelative(0.0F, 8.0F, 5.76F, 13.76F)
            quadTo(297.0F, 630.0F, 305.0F, 630.0F)
            quadToRelative(4.0F, 0.0F, 8.12F, -2.0F)
            quadToRelative(4.12F, -2.0F, 6.36F, -4.0F)
            lineToRelative(132.17F, -131.41F)
            lineToRelative(56.0F, 56.0F)
            lineToRelative(-131.89F, 132.65F)
            quadToRelative(-2.76F, 2.0F, -4.38F, 6.12F)
            quadToRelative(-1.62F, 4.12F, -1.62F, 8.12F)
            quadToRelative(0.0F, 8.0F, 5.88F, 13.88F)
            quadToRelative(5.88F, 5.88F, 13.88F, 5.88F)
            quadToRelative(4.0F, 0.0F, 7.62F, -1.5F)
            quadToRelative(3.62F, -1.5F, 6.62F, -4.5F)
            lineToRelative(132.65F, -131.89F)
            lineToRelative(56.0F, 56.0F)
            lineTo(459.76F, 766.0F)
            quadToRelative(-3.0F, 3.0F, -4.5F, 6.74F)
            reflectiveQuadToRelative(-1.5F, 7.74F)
            quadToRelative(0.0F, 8.0F, 6.26F, 13.76F)
            quadTo(466.28F, 800.0F, 474.28F, 800.0F)

            moveTo(473.52F, 887.41F)
            quadToRelative(-38.44F, 0.0F, -68.37F, -24.74F)
            quadToRelative(-29.93F, -24.74F, -36.37F, -62.7F)
            quadToRelative(-33.04F, -5.48F, -56.16F, -28.72F)
            quadToRelative(-23.12F, -23.24F, -28.6F, -56.28F)
            quadToRelative(-32.81F, -5.72F, -55.42F, -28.98F)
            quadToRelative(-22.62F, -23.26F, -28.34F, -55.3F)
            quadToRelative(-38.24F, -6.2F, -62.84F, -35.87F)
            quadToRelative(-24.6F, -29.67F, -24.6F, -69.35F)
            quadToRelative(0.0F, -21.19F, 8.1F, -41.01F)
            quadToRelative(8.1F, -19.82F, 23.05F, -34.77F)
            lineToRelative(232.0F, -231.24F)
            lineTo(511.52F, 354.0F)
            quadToRelative(2.48F, 3.24F, 6.48F, 4.86F)
            quadToRelative(4.0F, 1.62F, 8.24F, 1.62F)
            quadToRelative(8.76F, 0.0F, 14.52F, -5.5F)
            quadToRelative(5.76F, -5.5F, 5.76F, -14.26F)
            quadToRelative(0.0F, -4.0F, -1.62F, -8.36F)
            quadToRelative(-1.62F, -4.36F, -4.62F, -6.36F)
            lineTo(396.57F, 182.28F)
            quadToRelative(-10.76F, -11.76F, -26.1F, -17.02F)
            quadTo(355.13F, 160.0F, 338.17F, 160.0F)
            quadToRelative(-16.2F, 0.0F, -31.43F, 5.26F)
            quadToRelative(-15.24F, 5.26F, -27.0F, 17.02F)
            lineTo(142.09F, 320.7F)
            quadToRelative(-8.28F, 8.28F, -13.92F, 20.52F)
            reflectiveQuadToRelative(-7.4F, 25.43F)
            quadToRelative(-1.76F, 10.56F, 0.24F, 21.63F)
            quadToRelative(2.0F, 11.07F, 7.04F, 21.11F)
            lineToRelative(-63.26F, 63.26F)
            quadToRelative(-16.76F, -21.57F, -25.36F, -49.78F)
            quadToRelative(-8.6F, -28.22F, -6.84F, -56.22F)
            quadToRelative(1.76F, -30.63F, 13.64F, -58.33F)
            quadToRelative(11.88F, -27.7F, 33.84F, -49.65F)
            lineToRelative(137.41F, -137.65F)
            quadToRelative(24.72F, -23.96F, 56.37F, -36.2F)
            quadToRelative(31.65F, -12.24F, 64.33F, -12.24F)
            quadToRelative(33.39F, 0.0F, 64.68F, 12.24F)
            quadToRelative(31.29F, 12.24F, 55.01F, 35.48F)
            lineToRelative(9.09F, 8.85F)
            lineToRelative(8.85F, -8.85F)
            quadToRelative(24.24F, -22.76F, 55.89F, -35.24F)
            quadToRelative(31.65F, -12.48F, 64.8F, -12.48F)
            quadToRelative(32.67F, 0.0F, 64.21F, 12.72F)
            quadToRelative(31.53F, 12.72F, 55.49F, 36.67F)
            lineTo(878.5F, 284.04F)
            quadToRelative(23.96F, 23.96F, 36.44F, 56.47F)
            quadToRelative(12.48F, 32.51F, 12.48F, 65.66F)
            quadToRelative(0.0F, 33.39F, -12.84F, 65.88F)
            quadToRelative(-12.84F, 32.49F, -37.27F, 56.92F)
            lineTo(549.78F, 855.5F)
            quadToRelative(-15.2F, 15.2F, -34.89F, 23.56F)
            quadToRelative(-19.69F, 8.36F, -41.37F, 8.36F)

            moveTo(366.15F, 314.26F)
            close()
        }.build()
        return _handshake!!
    }
private var _handshake: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconHandshakePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Handshake, contentDescription = null)
    }
}