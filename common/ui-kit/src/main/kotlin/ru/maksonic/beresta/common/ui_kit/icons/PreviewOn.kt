package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 02.08.2023
 */
val AppIcon.PreviewOn: ImageVector
    get() {
        if (_previewOn != null) {
            return _previewOn!!
        }
        _previewOn = ImageVector.Builder(
            name = "PreviewOn",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(203.59F, 847.41F)
            quadToRelative(-37.54F, 0.0F, -64.27F, -26.73F)
            quadToRelative(-26.73F, -26.73F, -26.73F, -64.27F)
            verticalLineToRelative(-552.83F)
            quadToRelative(0.0F, -37.54F, 26.73F, -64.27F)
            quadToRelative(26.73F, -26.73F, 64.27F, -26.73F)
            horizontalLineToRelative(552.83F)
            quadToRelative(37.54F, 0.0F, 64.27F, 26.73F)
            quadToRelative(26.73F, 26.73F, 26.73F, 64.27F)
            verticalLineToRelative(552.83F)
            quadToRelative(0.0F, 37.54F, -26.73F, 64.27F)
            quadToRelative(-26.73F, 26.73F, -64.27F, 26.73F)
            lineTo(203.59F, 847.41F)

            moveTo(200.0F, 760.0F)
            horizontalLineToRelative(560.0F)
            verticalLineToRelative(-480.0F)
            lineTo(200.0F, 280.0F)
            verticalLineToRelative(480.0F)

            moveTo(480.0F, 680.0F)
            quadToRelative(-82.0F, 0.0F, -146.5F, -44.5F)
            reflectiveQuadTo(240.0F, 520.0F)
            quadToRelative(29.0F, -71.0F, 93.5F, -115.5F)
            reflectiveQuadTo(480.0F, 360.0F)
            quadToRelative(82.0F, 0.0F, 146.5F, 44.5F)
            reflectiveQuadTo(720.0F, 520.0F)
            quadToRelative(-29.0F, 71.0F, -93.5F, 115.5F)
            reflectiveQuadTo(480.0F, 680.0F)

            moveTo(480.0F, 620.0F)
            quadToRelative(56.0F, 0.0F, 102.0F, -26.5F)
            reflectiveQuadToRelative(72.0F, -73.5F)
            quadToRelative(-26.0F, -47.0F, -72.0F, -73.5F)
            reflectiveQuadTo(480.0F, 420.0F)
            quadToRelative(-56.0F, 0.0F, -102.0F, 26.5F)
            reflectiveQuadTo(306.0F, 520.0F)
            quadToRelative(26.0F, 47.0F, 72.0F, 73.5F)
            reflectiveQuadTo(480.0F, 620.0F)

            moveTo(480.0F, 520.0F)

            moveTo(480.0F, 580.0F)
            quadToRelative(25.0F, 0.0F, 42.5F, -17.5F)
            reflectiveQuadTo(540.0F, 520.0F)
            quadToRelative(0.0F, -25.0F, -17.5F, -42.5F)
            reflectiveQuadTo(480.0F, 460.0F)
            quadToRelative(-25.0F, 0.0F, -42.5F, 17.5F)
            reflectiveQuadTo(420.0F, 520.0F)
            quadToRelative(0.0F, 25.0F, 17.5F, 42.5F)
            reflectiveQuadTo(480.0F, 580.0F)
            close()
        }.build()
        return _previewOn!!
    }
private var _previewOn: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconPreviewEnabledPreview() {
    Image(imageVector = AppIcon.PreviewOn, contentDescription = null)
}