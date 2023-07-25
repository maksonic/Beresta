package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 15.07.2023
 */
val AppIcon.PhonelinkLock: ImageVector
    get() {
        if (_phonelinkLock != null) {
            return _phonelinkLock!!
        }
        _phonelinkLock = ImageVector.Builder(
            name = "PhonelinkLock",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(635.91F, 640.0F)
            quadToRelative(-14.0F, 0.0F, -24.0F, -10.0F)
            reflectiveQuadToRelative(-10.0F, -24.0F)
            verticalLineToRelative(-132.0F)
            quadToRelative(0.0F, -14.0F, 10.0F, -24.0F)
            reflectiveQuadToRelative(24.0F, -10.0F)
            horizontalLineToRelative(6.0F)
            verticalLineToRelative(-40.0F)
            quadToRelative(0.0F, -33.0F, 23.5F, -56.5F)
            reflectiveQuadToRelative(56.5F, -23.5F)
            quadToRelative(33.0F, 0.0F, 56.5F, 23.5F)
            reflectiveQuadToRelative(23.5F, 56.5F)
            verticalLineToRelative(40.0F)
            horizontalLineToRelative(6.0F)
            quadToRelative(14.0F, 0.0F, 24.0F, 10.0F)
            reflectiveQuadToRelative(10.0F, 24.0F)
            verticalLineToRelative(132.0F)
            quadToRelative(0.0F, 14.0F, -10.0F, 24.0F)
            reflectiveQuadToRelative(-24.0F, 10.0F)
            horizontalLineToRelative(-172.0F)

            moveTo(681.91F, 440.0F)
            horizontalLineToRelative(80.0F)
            verticalLineToRelative(-40.0F)
            quadToRelative(0.0F, -17.0F, -11.5F, -28.5F)
            reflectiveQuadToRelative(-28.5F, -11.5F)
            quadToRelative(-17.0F, 0.0F, -28.5F, 11.5F)
            reflectiveQuadToRelative(-11.5F, 28.5F)
            verticalLineToRelative(40.0F)

            moveTo(283.59F, 927.41F)
            quadToRelative(-37.54F, 0.0F, -64.27F, -26.73F)
            quadToRelative(-26.73F, -26.73F, -26.73F, -64.27F)
            verticalLineToRelative(-712.83F)
            quadToRelative(0.0F, -37.54F, 26.73F, -64.27F)
            quadToRelative(26.73F, -26.73F, 64.27F, -26.73F)
            horizontalLineToRelative(392.83F)
            quadToRelative(37.54F, 0.0F, 64.27F, 26.73F)
            quadToRelative(26.73F, 26.73F, 26.73F, 64.27F)
            lineTo(767.41F, 280.0F)
            horizontalLineToRelative(-91.0F)
            verticalLineToRelative(-36.41F)
            lineTo(283.59F, 243.59F)
            verticalLineToRelative(472.83F)
            horizontalLineToRelative(392.83F)
            lineTo(676.41F, 680.0F)
            horizontalLineToRelative(91.0F)
            verticalLineToRelative(156.41F)
            quadToRelative(0.0F, 37.54F, -26.73F, 64.27F)
            quadToRelative(-26.73F, 26.73F, -64.27F, 26.73F)
            lineTo(283.59F, 927.41F)

            moveTo(283.59F, 796.41F)
            verticalLineToRelative(40.0F)
            horizontalLineToRelative(392.83F)
            verticalLineToRelative(-40.0F)
            lineTo(283.59F, 796.41F)

            moveTo(283.59F, 163.59F)
            horizontalLineToRelative(392.83F)
            verticalLineToRelative(-40.0F)
            lineTo(283.59F, 123.59F)
            verticalLineToRelative(40.0F)

            moveTo(283.59F, 163.59F)
            verticalLineToRelative(-40.0F)

            moveTo(283.59F, 796.41F)
            verticalLineToRelative(40.0F)
            close()
        }.build()
        return _phonelinkLock!!
    }
private var _phonelinkLock: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconPhonelinkLockPreview() {
    Image(imageVector = AppIcon.PhonelinkLock, contentDescription = null)
}