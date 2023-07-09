package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 07.07.2023
 */
val AppIcon.CornerRadius: ImageVector
    get() {
        if (_cornerRadius != null) {
            return _cornerRadius!!
        }
        _cornerRadius = ImageVector.Builder(
            name = "CornerRadius",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(764.3F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-91.0F)

            moveTo(764.3F, 684.3F)
            verticalLineToRelative(-84.78F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(84.78F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 521.43F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 684.3F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 358.57F)
            verticalLineToRelative(-82.87F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(82.87F)
            horizontalLineToRelative(-91.0F)

            moveTo(104.69F, 195.7F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-91.0F)

            moveTo(275.7F, 195.7F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(601.44F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(438.57F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(275.7F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(82.87F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-82.87F)

            moveTo(104.7F, 855.3F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(91.0F)
            horizontalLineToRelative(-91.0F)

            moveTo(764.3F, 519.52F)
            verticalLineToRelative(-206.93F)
            quadToRelative(0.0F, -48.71F, -34.09F, -82.8F)
            reflectiveQuadToRelative(-82.8F, -34.09F)
            lineTo(438.57F, 195.7F)
            verticalLineToRelative(-91.0F)
            horizontalLineToRelative(208.79F)
            quadToRelative(86.65F, 0.0F, 147.3F, 60.65F)
            quadTo(855.3F, 226.0F, 855.3F, 312.59F)
            verticalLineToRelative(206.93F)
            horizontalLineToRelative(-91.0F)
            close()
        }.build()
        return _cornerRadius!!
    }
private var _cornerRadius: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconCornerRadiusPreview() {
    Image(imageVector = AppIcon.CornerRadius, contentDescription = null)
}