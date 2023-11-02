package ru.maksonic.beresta.common.ui_kit.icons.notifications

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon

/**
 * @Author maksonic on 15.09.2023
 */
val AppIcon.Notifications: ImageVector
    get() {
        if (_notifications != null) {
            return _notifications!!
        }
        _notifications = ImageVector.Builder(
            name = "Notifications",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(197.37F, 768.13F)
            quadToRelative(-19.15F, 0.0F, -32.33F, -13.17F)
            reflectiveQuadToRelative(-13.17F, -32.33F)
            quadToRelative(0.0F, -19.15F, 13.17F, -32.33F)
            reflectiveQuadToRelative(32.33F, -13.17F)
            lineTo(229.0F, 677.13F)
            verticalLineToRelative(-271.87F)
            quadToRelative(0.0F, -86.11F, 51.31F, -153.6F)
            quadToRelative(51.31F, -67.49F, 134.18F, -88.21F)
            verticalLineToRelative(-26.09F)
            quadToRelative(0.0F, -27.29F, 19.1F, -46.4F)
            quadToRelative(19.1F, -19.1F, 46.4F, -19.1F)
            reflectiveQuadToRelative(46.4F, 19.1F)
            quadTo(545.5F, 110.08F, 545.5F, 137.37F)
            verticalLineToRelative(26.09F)
            quadToRelative(83.11F, 20.72F, 134.3F, 88.09F)
            quadTo(731.0F, 318.91F, 731.0F, 405.26F)
            verticalLineToRelative(271.87F)
            horizontalLineToRelative(31.63F)
            quadToRelative(19.15F, 0.0F, 32.33F, 13.17F)
            reflectiveQuadToRelative(13.17F, 32.33F)
            quadToRelative(0.0F, 19.15F, -13.17F, 32.33F)
            reflectiveQuadToRelative(-32.33F, 13.17F)
            lineTo(197.37F, 768.13F)

            moveTo(480.0F, 461.2F)

            moveTo(480.24F, 890.76F)
            quadToRelative(-34.18F, 0.0F, -58.53F, -24.27F)
            quadToRelative(-24.34F, -24.27F, -24.34F, -58.36F)
            horizontalLineToRelative(165.5F)
            quadToRelative(0.0F, 34.19F, -24.27F, 58.41F)
            quadToRelative(-24.27F, 24.22F, -58.36F, 24.22F)

            moveTo(320.0F, 677.13F)
            horizontalLineToRelative(320.0F)
            verticalLineToRelative(-271.87F)
            quadToRelative(0.0F, -66.0F, -47.0F, -113.0F)
            reflectiveQuadToRelative(-113.0F, -47.0F)
            quadToRelative(-66.0F, 0.0F, -113.0F, 47.0F)
            reflectiveQuadToRelative(-47.0F, 113.0F)
            verticalLineToRelative(271.87F)
            close()
        }.build()
        return _notifications!!
    }
private var _notifications: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconNotificationsPreview() {
    Image(imageVector = AppIcon.Notifications, contentDescription = null)
}