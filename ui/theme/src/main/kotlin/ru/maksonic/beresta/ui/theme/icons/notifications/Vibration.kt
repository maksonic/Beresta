package ru.maksonic.beresta.ui.theme.icons.notifications

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.icons.AppIcon

/**
 * @Author maksonic on 15.09.2023
 */
val AppIcon.Vibration: ImageVector
    get() {
        if (_vibration != null) {
            return _vibration!!
        }
        _vibration = ImageVector.Builder(
            name = "Vibration",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(40.0F, 360.0F)
            quadToRelative(17.0F, 0.0F, 28.5F, 11.5F)
            reflectiveQuadTo(80.0F, 400.0F)
            verticalLineToRelative(160.0F)
            quadToRelative(0.0F, 17.0F, -11.5F, 28.5F)
            reflectiveQuadTo(40.0F, 600.0F)
            quadToRelative(-17.0F, 0.0F, -28.5F, -11.5F)
            reflectiveQuadTo(0.0F, 560.0F)
            verticalLineToRelative(-160.0F)
            quadToRelative(0.0F, -17.0F, 11.5F, -28.5F)
            reflectiveQuadTo(40.0F, 360.0F)

            moveTo(160.0F, 280.0F)
            quadToRelative(17.0F, 0.0F, 28.5F, 11.5F)
            reflectiveQuadTo(200.0F, 320.0F)
            verticalLineToRelative(320.0F)
            quadToRelative(0.0F, 17.0F, -11.5F, 28.5F)
            reflectiveQuadTo(160.0F, 680.0F)
            quadToRelative(-17.0F, 0.0F, -28.5F, -11.5F)
            reflectiveQuadTo(120.0F, 640.0F)
            verticalLineToRelative(-320.0F)
            quadToRelative(0.0F, -17.0F, 11.5F, -28.5F)
            reflectiveQuadTo(160.0F, 280.0F)

            moveTo(920.0F, 360.0F)
            quadToRelative(17.0F, 0.0F, 28.5F, 11.5F)
            reflectiveQuadTo(960.0F, 400.0F)
            verticalLineToRelative(160.0F)
            quadToRelative(0.0F, 17.0F, -11.5F, 28.5F)
            reflectiveQuadTo(920.0F, 600.0F)
            quadToRelative(-17.0F, 0.0F, -28.5F, -11.5F)
            reflectiveQuadTo(880.0F, 560.0F)
            verticalLineToRelative(-160.0F)
            quadToRelative(0.0F, -17.0F, 11.5F, -28.5F)
            reflectiveQuadTo(920.0F, 360.0F)

            moveTo(800.0F, 280.0F)
            quadToRelative(17.0F, 0.0F, 28.5F, 11.5F)
            reflectiveQuadTo(840.0F, 320.0F)
            verticalLineToRelative(320.0F)
            quadToRelative(0.0F, 17.0F, -11.5F, 28.5F)
            reflectiveQuadTo(800.0F, 680.0F)
            quadToRelative(-17.0F, 0.0F, -28.5F, -11.5F)
            reflectiveQuadTo(760.0F, 640.0F)
            verticalLineToRelative(-320.0F)
            quadToRelative(0.0F, -17.0F, 11.5F, -28.5F)
            reflectiveQuadTo(800.0F, 280.0F)

            moveTo(331.0F, 840.0F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadTo(240.0F, 786.78F, 240.0F, 749.0F)
            verticalLineToRelative(-538.0F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadTo(293.22F, 120.0F, 331.0F, 120.0F)
            horizontalLineToRelative(298.0F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadTo(720.0F, 173.22F, 720.0F, 211.0F)
            verticalLineToRelative(538.0F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadTo(666.78F, 840.0F, 629.0F, 840.0F)
            lineTo(331.0F, 840.0F)

            moveTo(331.0F, 749.0F)
            horizontalLineToRelative(298.0F)
            verticalLineToRelative(-538.0F)
            lineTo(331.0F, 211.0F)
            verticalLineToRelative(538.0F)

            moveTo(331.0F, 749.0F)
            verticalLineToRelative(-538.0F)
            close()
        }.build()
        return _vibration!!
    }
private var _vibration: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconVibrationPreview() {
    Image(imageVector = AppIcon.Vibration, contentDescription = null)
}