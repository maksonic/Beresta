package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 28.07.2023
 */
val AppIcon.Repartition: ImageVector
    get() {
        if (_repartition != null) {
            return _repartition!!
        }
        _repartition = ImageVector.Builder(
            name = "Repartition",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveToRelative(246.15F, 315.46F)
            lineToRelative(26.63F, 26.63F)
            quadToRelative(13.19F, 13.19F, 13.08F, 31.49F)
            quadToRelative(-0.12F, 18.29F, -14.03F, 32.21F)
            quadToRelative(-13.67F, 13.67F, -32.45F, 13.67F)
            quadToRelative(-18.77F, 0.0F, -32.44F, -13.67F)
            lineToRelative(-103.0F, -103.76F)
            quadToRelative(-13.67F, -13.67F, -13.67F, -32.07F)
            quadToRelative(0.0F, -18.39F, 13.67F, -32.06F)
            lineToRelative(104.0F, -103.76F)
            quadToRelative(12.67F, -12.67F, 31.44F, -12.55F)
            quadToRelative(18.77F, 0.12F, 33.16F, 13.51F)
            quadToRelative(13.19F, 13.19F, 13.31F, 31.37F)
            quadToRelative(0.12F, 18.17F, -13.08F, 31.37F)
            lineToRelative(-26.63F, 26.63F)
            horizontalLineToRelative(431.46F)
            quadToRelative(70.54F, 0.0F, 120.53F, 49.87F)
            quadToRelative(49.99F, 49.87F, 49.99F, 120.41F)
            reflectiveQuadToRelative(-49.99F, 120.53F)
            quadToRelative(-49.99F, 49.99F, -120.53F, 49.99F)
            lineTo(157.37F, 565.26F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadToRelative(-13.17F, -32.33F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadToRelative(32.33F, -13.17F)
            horizontalLineToRelative(520.24F)
            quadToRelative(32.76F, 0.0F, 56.14F, -23.38F)
            quadToRelative(23.38F, -23.38F, 23.38F, -56.14F)
            quadToRelative(0.0F, -32.76F, -23.38F, -56.02F)
            quadToRelative(-23.38F, -23.26F, -56.14F, -23.26F)
            lineTo(246.15F, 315.46F)

            moveTo(111.87F, 797.13F)
            verticalLineToRelative(-70.43F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(554.26F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadToRelative(26.61F, 26.61F, 26.61F, 64.39F)
            verticalLineToRelative(70.43F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadTo(794.91F, 888.13F, 757.13F, 888.13F)
            lineTo(202.87F, 888.13F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadToRelative(-26.61F, -26.61F, -26.61F, -64.39F)

            moveTo(202.87F, 797.13F)
            horizontalLineToRelative(127.02F)
            verticalLineToRelative(-70.43F)
            lineTo(202.87F, 726.7F)
            verticalLineToRelative(70.43F)

            moveTo(416.11F, 797.13F)
            lineTo(543.13F, 797.13F)
            verticalLineToRelative(-70.43F)
            lineTo(416.11F, 726.7F)
            verticalLineToRelative(70.43F)

            moveTo(630.11F, 797.13F)
            lineTo(757.13F, 797.13F)
            verticalLineToRelative(-70.43F)
            lineTo(630.11F, 726.7F)
            verticalLineToRelative(70.43F)
            close()
        }.build()
        return _repartition!!
    }
private var _repartition: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconRepartitionPreview() {
    Image(imageVector = AppIcon.Repartition, contentDescription = null)
}