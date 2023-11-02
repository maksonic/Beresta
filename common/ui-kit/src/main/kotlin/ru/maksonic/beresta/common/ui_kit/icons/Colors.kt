package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 15.09.2023
 */
val AppIcon.Colors: ImageVector
    get() {
        if (_colors != null) {
            return _colors!!
        }
        _colors = ImageVector.Builder(
            name = "Colors",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(331.41F, 824.07F)
            lineTo(95.93F, 588.83F)
            quadToRelative(-12.15F, -12.15F, -18.11F, -26.78F)
            quadToRelative(-5.96F, -14.63F, -5.96F, -30.5F)
            quadToRelative(0.0F, -15.63F, 5.96F, -30.26F)
            quadToRelative(5.96F, -14.63F, 18.11F, -26.78F)
            lineTo(318.76F, 252.91F)
            lineToRelative(-75.0F, -75.24F)
            quadToRelative(-14.44F, -14.44F, -14.82F, -34.47F)
            quadToRelative(-0.38F, -20.03F, 14.06F, -35.23F)
            quadToRelative(14.44F, -15.44F, 35.35F, -15.31F)
            quadToRelative(20.91F, 0.12F, 36.11F, 15.31F)
            lineTo(680.74F, 474.5F)
            quadToRelative(12.15F, 12.15F, 17.73F, 26.78F)
            quadToRelative(5.58F, 14.63F, 5.58F, 30.26F)
            quadToRelative(0.0F, 15.87F, -5.58F, 30.5F)
            quadToRelative(-5.58F, 14.63F, -17.73F, 26.78F)
            lineTo(445.5F, 824.07F)
            quadToRelative(-11.91F, 11.91F, -26.66F, 17.99F)
            quadToRelative(-14.75F, 6.08F, -30.38F, 6.08F)
            quadToRelative(-15.63F, 0.0F, -30.26F, -5.96F)
            quadToRelative(-14.63F, -5.96F, -26.78F, -18.11F)

            moveTo(388.22F, 322.61F)
            lineTo(181.15F, 529.67F)
            horizontalLineToRelative(414.13F)
            lineTo(388.22F, 322.61F)

            moveTo(797.5F, 848.13F)
            quadToRelative(-37.44F, 0.0F, -63.15F, -26.7F)
            quadToRelative(-25.72F, -26.7F, -25.72F, -65.13F)
            quadToRelative(0.0F, -26.76F, 12.42F, -50.64F)
            quadToRelative(12.42F, -23.88F, 29.42F, -46.16F)
            lineToRelative(20.91F, -25.43F)
            quadToRelative(10.2F, -12.19F, 26.25F, -12.58F)
            quadToRelative(16.06F, -0.38F, 26.25F, 11.81F)
            lineToRelative(21.67F, 26.2F)
            quadToRelative(16.24F, 22.28F, 29.4F, 46.16F)
            quadToRelative(13.16F, 23.88F, 13.16F, 50.64F)
            quadToRelative(0.0F, 38.44F, -26.6F, 65.13F)
            quadToRelative(-26.6F, 26.7F, -64.03F, 26.7F)
            close()
        }.build()
        return _colors!!
    }
private var _colors: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconColorsPreview() {
    Image(imageVector = AppIcon.Colors, contentDescription = null)
}