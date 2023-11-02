package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 06.03.2023
 */
val AppIcon.AddImage: ImageVector
    get() {
        if (_addImage != null) {
            return _addImage!!
        }
        _addImage = ImageVector.Builder(
            name = "AddImage",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(201.43F, 848.13F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadToRelative(-26.61F, -26.61F, -26.61F, -64.39F)
            lineTo(110.43F, 202.87F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(304.22F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadToRelative(13.17F, 32.33F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadToRelative(-32.33F, 13.17F)
            lineTo(201.43F, 202.87F)
            verticalLineToRelative(554.26F)
            horizontalLineToRelative(554.26F)
            lineTo(755.7F, 452.91F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            quadToRelative(13.17F, -13.17F, 32.33F, -13.17F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadToRelative(13.17F, 32.33F)
            lineTo(846.7F, 757.13F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)
            lineTo(201.43F, 848.13F)

            moveTo(724.07F, 360.0F)
            quadToRelative(-17.81F, 0.0F, -29.86F, -12.05F)
            reflectiveQuadToRelative(-12.05F, -29.86F)
            verticalLineToRelative(-41.67F)
            horizontalLineToRelative(-41.68F)
            quadToRelative(-17.71F, 0.0F, -29.81F, -12.05F)
            quadToRelative(-12.1F, -12.05F, -12.1F, -29.86F)
            reflectiveQuadToRelative(12.05F, -29.86F)
            quadToRelative(12.05F, -12.05F, 29.86F, -12.05F)
            horizontalLineToRelative(41.67F)
            verticalLineToRelative(-41.68F)
            quadToRelative(0.0F, -17.71F, 12.05F, -29.81F)
            quadTo(706.25F, 109.0F, 724.07F, 109.0F)
            reflectiveQuadToRelative(29.86F, 12.05F)
            quadToRelative(12.05F, 12.05F, 12.05F, 29.86F)
            verticalLineToRelative(41.67F)
            horizontalLineToRelative(41.68F)
            quadToRelative(17.71F, 0.0F, 29.81F, 12.05F)
            quadToRelative(12.1F, 12.05F, 12.1F, 29.86F)
            reflectiveQuadToRelative(-12.05F, 29.86F)
            quadToRelative(-12.05F, 12.05F, -29.86F, 12.05F)
            horizontalLineToRelative(-41.67F)
            verticalLineToRelative(41.68F)
            quadToRelative(0.0F, 17.71F, -12.05F, 29.81F)
            quadTo(741.88F, 360.0F, 724.07F, 360.0F)

            moveTo(284.3F, 680.0F)
            horizontalLineToRelative(388.28F)
            quadToRelative(13.91F, 0.0F, 20.63F, -12.67F)
            quadToRelative(6.72F, -12.67F, -2.24F, -24.11F)
            lineTo(586.96F, 504.11F)
            quadToRelative(-6.88F, -9.44F, -18.35F, -9.44F)
            quadToRelative(-11.47F, 0.0F, -18.43F, 9.44F)
            lineTo(448.57F, 640.0F)
            lineToRelative(-71.61F, -95.89F)
            quadToRelative(-6.88F, -9.44F, -18.35F, -9.44F)
            quadToRelative(-11.47F, 0.0F, -18.43F, 9.44F)
            lineToRelative(-74.26F, 99.11F)
            quadToRelative(-8.96F, 11.44F, -2.24F, 24.11F)
            quadTo(270.39F, 680.0F, 284.3F, 680.0F)

            moveTo(201.43F, 440.0F)
            verticalLineToRelative(317.13F)
            lineTo(201.43F, 440.0F)
            close()
        }.build()
        return _addImage!!
    }
private var _addImage: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconAddImagePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.AddImage, contentDescription = null)
    }
}