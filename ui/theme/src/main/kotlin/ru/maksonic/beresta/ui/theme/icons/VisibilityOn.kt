package ru.maksonic.beresta.ui.theme.icons

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
val AppIcon.VisibilityOn: ImageVector
    get() {
        if (_visibilityOn != null) {
            return _visibilityOn!!
        }
        _visibilityOn = ImageVector.Builder(
            name = "VisibilityOn",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 640.0F)
            quadToRelative(75.0F, 0.0F, 127.5F, -52.5F)
            reflectiveQuadTo(660.0F, 460.0F)
            quadToRelative(0.0F, -75.0F, -52.5F, -127.5F)
            reflectiveQuadTo(480.0F, 280.0F)
            quadToRelative(-75.0F, 0.0F, -127.5F, 52.5F)
            reflectiveQuadTo(300.0F, 460.0F)
            quadToRelative(0.0F, 75.0F, 52.5F, 127.5F)
            reflectiveQuadTo(480.0F, 640.0F)

            moveTo(480.04F, 562.98F)
            quadToRelative(-42.89F, 0.0F, -72.96F, -30.02F)
            quadToRelative(-30.07F, -30.02F, -30.07F, -72.91F)
            reflectiveQuadToRelative(30.02F, -72.96F)
            quadToRelative(30.02F, -30.07F, 72.91F, -30.07F)
            reflectiveQuadToRelative(72.96F, 30.02F)
            quadToRelative(30.07F, 30.02F, 30.07F, 72.91F)
            reflectiveQuadToRelative(-30.02F, 72.96F)
            quadToRelative(-30.02F, 30.07F, -72.91F, 30.07F)

            moveTo(480.0F, 767.41F)
            quadToRelative(-140.67F, 0.0F, -257.23F, -74.82F)
            quadToRelative(-116.56F, -74.82F, -176.75F, -201.25F)
            quadToRelative(-3.43F, -5.66F, -4.57F, -14.14F)
            quadToRelative(-1.14F, -8.49F, -1.14F, -17.2F)
            reflectiveQuadToRelative(1.14F, -17.2F)
            quadToRelative(1.14F, -8.49F, 4.57F, -14.14F)
            quadToRelative(60.19F, -126.43F, 176.75F, -201.25F)
            quadTo(339.33F, 152.59F, 480.0F, 152.59F)
            reflectiveQuadToRelative(257.23F, 74.82F)
            quadToRelative(116.56F, 74.82F, 176.75F, 201.25F)
            quadToRelative(3.43F, 5.66F, 4.57F, 14.14F)
            quadToRelative(1.14F, 8.49F, 1.14F, 17.2F)
            reflectiveQuadToRelative(-1.14F, 17.2F)
            quadToRelative(-1.14F, 8.49F, -4.57F, 14.14F)
            quadToRelative(-60.19F, 126.43F, -176.75F, 201.25F)
            quadTo(620.67F, 767.41F, 480.0F, 767.41F)

            moveTo(480.0F, 460.0F)

            moveTo(480.02F, 680.0F)
            quadToRelative(112.74F, 0.0F, 207.0F, -59.62F)
            quadTo(781.28F, 560.76F, 831.28F, 460.0F)
            quadToRelative(-50.0F, -100.76F, -144.28F, -160.38F)
            quadTo(592.72F, 240.0F, 479.98F, 240.0F)
            quadToRelative(-112.74F, 0.0F, -207.0F, 59.62F)
            quadTo(178.72F, 359.24F, 128.72F, 460.0F)
            quadToRelative(50.0F, 100.76F, 144.28F, 160.38F)
            quadTo(367.28F, 680.0F, 480.02F, 680.0F)
            close()
        }.build()
        return _visibilityOn!!
    }
private var _visibilityOn: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconVisibilityOnPreview() {
    Image(imageVector = AppIcon.VisibilityOn, contentDescription = null)
}