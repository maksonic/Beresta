package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_theme.BerestaTheme

/**
 * @Author maksonic on 01.06.2023
 */
val AppIcon.RemoveFolder: ImageVector
    get() {
        if (_removeFolder != null) {
            return _removeFolder!!
        }
        _removeFolder = ImageVector.Builder(
            name = "RemoveFolder",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(580.0F, 680.0F)
            horizontalLineToRelative(80.0F)
            quadToRelative(25.0F, 0.0F, 42.5F, -17.5F)
            reflectiveQuadTo(720.0F, 620.0F)
            verticalLineToRelative(-160.0F)
            horizontalLineToRelative(10.0F)
            quadToRelative(13.0F, 0.0F, 21.5F, -8.5F)
            reflectiveQuadTo(760.0F, 430.0F)
            quadToRelative(0.0F, -13.0F, -8.5F, -21.5F)
            reflectiveQuadTo(730.0F, 400.0F)
            horizontalLineToRelative(-70.0F)
            verticalLineToRelative(-8.09F)
            quadToRelative(0.0F, -13.96F, -8.98F, -22.93F)
            quadTo(642.04F, 360.0F, 628.09F, 360.0F)
            horizontalLineToRelative(-16.17F)
            quadToRelative(-13.96F, 0.0F, -22.93F, 8.98F)
            quadTo(580.0F, 377.96F, 580.0F, 391.91F)
            lineTo(580.0F, 400.0F)
            horizontalLineToRelative(-70.0F)
            quadToRelative(-13.0F, 0.0F, -21.5F, 8.5F)
            reflectiveQuadTo(480.0F, 430.0F)
            quadToRelative(0.0F, 13.0F, 8.5F, 21.5F)
            reflectiveQuadTo(510.0F, 460.0F)
            horizontalLineToRelative(10.0F)
            verticalLineToRelative(160.0F)
            quadToRelative(0.0F, 25.0F, 17.5F, 42.5F)
            reflectiveQuadTo(580.0F, 680.0F)

            moveTo(580.0F, 620.0F)
            verticalLineToRelative(-160.0F)
            horizontalLineToRelative(80.0F)
            verticalLineToRelative(160.0F)
            horizontalLineToRelative(-80.0F)

            moveTo(162.87F, 808.13F)
            quadToRelative(-37.78F, 0.0F, -64.39F, -26.61F)
            quadTo(71.87F, 754.91F, 71.87F, 717.13F)
            verticalLineToRelative(-477.85F)
            quadToRelative(0.0F, -37.78F, 26.61F, -64.39F)
            quadToRelative(26.61F, -26.61F, 64.39F, -26.61F)
            horizontalLineToRelative(196.0F)
            quadToRelative(18.21F, 0.0F, 34.71F, 6.96F)
            quadToRelative(16.5F, 6.96F, 28.94F, 19.39F)
            lineTo(480.0F, 231.87F)
            horizontalLineToRelative(317.13F)
            quadToRelative(37.78F, 0.0F, 64.39F, 26.61F)
            quadToRelative(26.61F, 26.61F, 26.61F, 64.39F)
            verticalLineToRelative(394.26F)
            quadToRelative(0.0F, 37.78F, -26.61F, 64.39F)
            quadToRelative(-26.61F, 26.61F, -64.39F, 26.61F)
            lineTo(162.87F, 808.13F)

            moveTo(162.87F, 717.13F)
            horizontalLineToRelative(634.26F)
            verticalLineToRelative(-394.26F)
            lineTo(442.46F, 322.87F)
            lineToRelative(-83.59F, -83.59F)
            lineTo(162.87F, 239.28F)
            verticalLineToRelative(477.85F)

            moveTo(162.87F, 717.13F)
            verticalLineToRelative(-477.85F)
            close()
        }.build()
        return _removeFolder!!
    }
private var _removeFolder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconRemoveFolderPreview() {
    BerestaTheme {
        Image(imageVector = AppIcon.RemoveFolder, contentDescription = null)
    }
}