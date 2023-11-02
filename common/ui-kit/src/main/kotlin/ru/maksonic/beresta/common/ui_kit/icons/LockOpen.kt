package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 30.07.2023
 */
val AppIcon.LockOpen: ImageVector
    get() {
        if (_lockOpen != null) {
            return _lockOpen!!
        }
        _lockOpen = ImageVector.Builder(
            name = "LockOpen",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(242.87F, 887.41F)
            quadToRelative(-37.54F, 0.0F, -64.27F, -26.73F)
            quadToRelative(-26.73F, -26.73F, -26.73F, -64.27F)
            verticalLineToRelative(-394.26F)
            quadToRelative(0.0F, -37.54F, 26.73F, -64.27F)
            quadToRelative(26.73F, -26.73F, 64.27F, -26.73F)
            horizontalLineToRelative(349.72F)
            verticalLineToRelative(-73.07F)
            quadToRelative(0.0F, -47.21F, -32.73F, -80.26F)
            quadTo(527.13F, 124.78F, 480.0F, 124.78F)
            quadToRelative(-38.27F, 0.0F, -66.97F, 22.51F)
            quadToRelative(-28.7F, 22.51F, -39.7F, 56.21F)
            quadToRelative(-5.67F, 15.67F, -20.09F, 25.13F)
            quadToRelative(-14.41F, 9.46F, -31.43F, 9.46F)
            quadToRelative(-19.29F, 0.0F, -31.98F, -13.39F)
            quadToRelative(-12.7F, -13.39F, -8.5F, -31.02F)
            quadTo(297.0F, 125.39F, 352.12F, 79.59F)
            quadToRelative(55.12F, -45.81F, 127.88F, -45.81F)
            quadToRelative(84.67F, 0.0F, 144.13F, 59.7F)
            quadToRelative(59.46F, 59.7F, 59.46F, 144.61F)
            verticalLineToRelative(73.07F)
            horizontalLineToRelative(33.54F)
            quadToRelative(37.54F, 0.0F, 64.27F, 26.73F)
            quadToRelative(26.73F, 26.73F, 26.73F, 64.27F)
            verticalLineToRelative(394.26F)
            quadToRelative(0.0F, 37.54F, -26.73F, 64.27F)
            quadToRelative(-26.73F, 26.73F, -64.27F, 26.73F)
            lineTo(242.87F, 887.41F)

            moveTo(242.87F, 796.41F)
            horizontalLineToRelative(474.26F)
            verticalLineToRelative(-394.26F)
            lineTo(242.87F, 402.15F)
            verticalLineToRelative(394.26F)

            moveTo(480.0F, 679.28F)
            quadToRelative(33.0F, 0.0F, 56.5F, -23.5F)
            reflectiveQuadToRelative(23.5F, -56.5F)
            quadToRelative(0.0F, -33.0F, -23.5F, -56.5F)
            reflectiveQuadToRelative(-56.5F, -23.5F)
            quadToRelative(-33.0F, 0.0F, -56.5F, 23.5F)
            reflectiveQuadToRelative(-23.5F, 56.5F)
            quadToRelative(0.0F, 33.0F, 23.5F, 56.5F)
            reflectiveQuadToRelative(56.5F, 23.5F)

            moveTo(242.87F, 796.41F)
            verticalLineToRelative(-394.26F)
            close()
        }.build()
        return _lockOpen!!
    }
private var _lockOpen: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconLockOpenPreview() {
    Image(imageVector = AppIcon.LockOpen, contentDescription = null)
}