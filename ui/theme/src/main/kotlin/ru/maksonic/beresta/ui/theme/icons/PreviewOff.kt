package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 02.08.2023
 */
val AppIcon.PreviewOff: ImageVector
    get() {
        if (_previewOff != null) {
            return _previewOff!!
        }
        _previewOff = ImageVector.Builder(
            name = "PreviewOff",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 680.0F)
            quadToRelative(-82.0F, 0.0F, -146.5F, -44.5F)
            reflectiveQuadTo(240.0F, 520.0F)
            quadToRelative(20.0F, -48.72F, 56.84F, -84.96F)
            quadToRelative(36.84F, -36.24F, 85.79F, -56.0F)
            lineToRelative(47.24F, 47.24F)
            quadToRelative(-39.96F, 10.28F, -71.79F, 34.14F)
            quadTo(326.24F, 484.28F, 306.0F, 520.0F)
            quadToRelative(26.0F, 47.0F, 72.0F, 73.5F)
            reflectiveQuadTo(480.0F, 620.0F)
            quadToRelative(30.72F, 0.0F, 59.2F, -8.36F)
            quadToRelative(28.48F, -8.36F, 51.96F, -24.08F)
            lineToRelative(42.76F, 42.76F)
            quadToRelative(-32.24F, 23.72F, -71.22F, 36.7F)
            quadTo(523.72F, 680.0F, 480.0F, 680.0F)

            moveTo(690.43F, 573.61F)
            lineTo(647.43F, 530.61F)
            quadToRelative(1.76F, -2.52F, 3.28F, -5.3F)
            quadToRelative(1.52F, -2.78F, 3.28F, -5.3F)
            quadToRelative(-17.04F, -31.33F, -44.37F, -54.11F)
            quadToRelative(-27.33F, -22.78F, -61.17F, -34.26F)
            lineTo(476.83F, 360.0F)
            quadToRelative(81.28F, 0.72F, 147.85F, 45.1F)
            quadTo(691.24F, 449.48F, 720.0F, 520.0F)
            quadToRelative(-5.76F, 14.28F, -12.9F, 27.68F)
            quadToRelative(-7.14F, 13.4F, -16.66F, 25.92F)

            moveTo(202.87F, 848.13F)
            quadToRelative(-37.54F, 0.0F, -64.27F, -26.73F)
            quadToRelative(-26.73F, -26.73F, -26.73F, -64.27F)
            verticalLineToRelative(-527.24F)
            quadToRelative(0.0F, -0.96F, 0.12F, -2.03F)
            quadToRelative(0.12F, -1.08F, 0.12F, -1.55F)
            lineToRelative(-32.7F, -32.69F)
            quadToRelative(-11.72F, -11.72F, -11.72F, -28.82F)
            quadToRelative(0.0F, -17.1F, 12.48F, -29.58F)
            quadTo(91.89F, 123.5F, 109.49F, 123.5F)
            reflectiveQuadToRelative(29.32F, 11.72F)
            lineToRelative(686.22F, 686.22F)
            quadTo(837.5F, 833.91F, 837.0F, 850.75F)
            quadToRelative(-0.5F, 16.84F, -12.98F, 29.32F)
            quadToRelative(-12.48F, 11.72F, -29.2F, 12.1F)
            quadToRelative(-16.72F, 0.38F, -29.2F, -12.1F)
            lineToRelative(-31.93F, -31.93F)
            lineTo(202.87F, 848.13F)

            moveTo(202.87F, 757.13F)
            horizontalLineToRelative(439.83F)
            lineTo(202.87F, 317.3F)
            verticalLineToRelative(439.83F)

            moveTo(848.13F, 731.3F)
            lineTo(760.0F, 643.17F)
            verticalLineToRelative(-324.61F)
            lineTo(435.39F, 318.57F)
            lineTo(228.7F, 111.87F)
            lineTo(757.13F, 111.87F)
            quadToRelative(37.54F, 0.0F, 64.27F, 26.73F)
            quadToRelative(26.73F, 26.73F, 26.73F, 64.27F)
            verticalLineToRelative(528.43F)
            close()
        }.build()
        return _previewOff!!
    }
private var _previewOff: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconPreviewOffPreview() {
    Image(imageVector = AppIcon.PreviewOff, contentDescription = null)
}