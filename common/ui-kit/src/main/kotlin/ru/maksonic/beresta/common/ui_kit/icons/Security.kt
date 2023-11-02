package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 03.08.2023
 */
val AppIcon.Security: ImageVector
    get() {
        if (_security != null) {
            return _security!!
        }
        _security = ImageVector.Builder(
            name = "Security",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(480.0F, 793.37F)
            quadToRelative(95.33F, -31.43F, 159.73F, -118.5F)
            quadTo(704.13F, 587.8F, 715.13F, 480.0F)
            lineTo(480.0F, 480.0F)
            verticalLineToRelative(-311.65F)
            lineToRelative(-237.13F, 88.8F)
            lineTo(242.87F, 462.0F)
            quadToRelative(0.0F, 7.48F, 2.0F, 18.0F)
            lineTo(480.0F, 480.0F)
            verticalLineToRelative(313.37F)

            moveTo(480.0F, 883.65F)
            quadToRelative(-7.72F, 0.0F, -14.67F, -1.12F)
            quadToRelative(-6.96F, -1.12F, -13.91F, -3.36F)
            quadToRelative(-136.67F, -45.48F, -218.11F, -169.61F)
            quadTo(151.87F, 585.43F, 151.87F, 444.0F)
            verticalLineToRelative(-186.85F)
            quadToRelative(0.0F, -28.59F, 16.41F, -51.46F)
            quadToRelative(16.41F, -22.87F, 42.76F, -33.07F)
            lineToRelative(237.13F, -88.8F)
            quadToRelative(15.67F, -5.72F, 31.83F, -5.72F)
            reflectiveQuadToRelative(31.83F, 5.72F)
            lineToRelative(237.13F, 88.8F)
            quadToRelative(26.35F, 10.2F, 42.76F, 33.07F)
            quadToRelative(16.41F, 22.87F, 16.41F, 51.46F)
            lineTo(808.13F, 444.0F)
            quadToRelative(0.0F, 141.43F, -81.43F, 265.57F)
            quadToRelative(-81.43F, 124.13F, -218.11F, 169.61F)
            quadToRelative(-6.96F, 2.24F, -13.91F, 3.36F)
            quadToRelative(-6.96F, 1.12F, -14.67F, 1.12F)
            close()
        }.build()
        return _security!!
    }
private var _security: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSecurityPreview() {
    Image(imageVector = AppIcon.Security, contentDescription = null)
}