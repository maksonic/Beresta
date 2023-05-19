package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 06.03.2023
 */
val AppIcon.Save: ImageVector
    get() {
        if (_save != null) {
            return _save!!
        }
        _save = ImageVector.Builder(
            name = "Save",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(202.87F, 848.13F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadToRelative(-26.61F, -26.61F, -26.61F, -64.39F)
            lineTo(111.87F, 202.87F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(442.93F)
            quadToRelative(18.15F, 0.0F, 34.81F, 6.96F)
            quadToRelative(16.65F, 6.96F, 29.09F, 19.39F)
            lineToRelative(112.09F, 112.09F)
            quadToRelative(12.44F, 12.44F, 19.39F, 29.09F)
            quadToRelative(6.96F, 16.65F, 6.96F, 34.81F)
            lineTo(848.13F, 757.13F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)
            lineTo(202.87F, 848.13F)

            moveTo(757.13F, 315.2F)
            lineTo(644.8F, 202.87F)
            lineTo(202.87F, 202.87F)
            verticalLineToRelative(554.26F)
            horizontalLineToRelative(554.26F)
            lineTo(757.13F, 315.2F)

            moveTo(480.0F, 717.13F)
            quadToRelative(50.0F, 0.0F, 85.0F, -35.0F)
            reflectiveQuadToRelative(35.0F, -85.0F)
            quadToRelative(0.0F, -50.0F, -35.0F, -85.0F)
            reflectiveQuadToRelative(-85.0F, -35.0F)
            quadToRelative(-50.0F, 0.0F, -85.0F, 35.0F)
            reflectiveQuadToRelative(-35.0F, 85.0F)
            quadToRelative(0.0F, 50.0F, 35.0F, 85.0F)
            reflectiveQuadToRelative(85.0F, 35.0F)

            moveTo(288.37F, 402.87F)
            horizontalLineToRelative(269.0F)
            quadToRelative(19.15F, 0.0F, 32.33F, -13.17F)
            reflectiveQuadToRelative(13.17F, -32.33F)
            verticalLineToRelative(-69.0F)
            quadToRelative(0.0F, -19.15F, -13.17F, -32.33F)
            reflectiveQuadTo(557.37F, 242.87F)
            horizontalLineToRelative(-269.0F)
            quadToRelative(-19.15F, 0.0F, -32.33F, 13.17F)
            reflectiveQuadTo(242.87F, 288.37F)
            verticalLineToRelative(69.0F)
            quadToRelative(0.0F, 19.15F, 13.17F, 32.33F)
            reflectiveQuadToRelative(32.33F, 13.17F)

            moveTo(202.87F, 315.2F)
            lineTo(202.87F, 757.13F)
            lineTo(202.87F, 202.87F)
            verticalLineToRelative(112.33F)
            close()
        }.build()
        return _save!!
    }
private var _save: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSavePreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.Save, contentDescription = null)
    }
}