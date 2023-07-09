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
val AppIcon.Shadow: ImageVector
    get() {
        if (_shadow != null) {
            return _shadow!!
        }
        _shadow = ImageVector.Builder(
            name = "Shadow",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(162.87F, 888.13F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadTo(71.87F, 834.91F, 71.87F, 797.13F)
            verticalLineToRelative(-474.26F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(69.0F)
            verticalLineToRelative(-69.0F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(474.26F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadToRelative(26.61F, 26.61F, 26.61F, 64.39F)
            verticalLineToRelative(474.26F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)
            horizontalLineToRelative(-69.0F)
            verticalLineToRelative(69.0F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadTo(674.91F, 888.13F, 637.13F, 888.13F)
            lineTo(162.87F, 888.13F)

            moveTo(322.87F, 637.13F)
            horizontalLineToRelative(474.26F)
            verticalLineToRelative(-474.26F)
            lineTo(322.87F, 162.87F)
            verticalLineToRelative(474.26F)
            close()
        }.build()
        return _shadow!!
    }
private var _shadow: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconShadowPreview() {
    Image(imageVector = AppIcon.Shadow, contentDescription = null)
}