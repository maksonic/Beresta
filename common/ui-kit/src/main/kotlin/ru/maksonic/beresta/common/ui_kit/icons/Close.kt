package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.Close: ImageVector
    get() {
        if (_close != null) {
            return _close!!
        }
        _close = materialIcon(name = "Close") {
            materialPath {
                moveToRelative(12.0F, 13.6F)
                lineToRelative(-4.8F, 4.8F)
                quadToRelative(-0.325F, 0.325F, -0.8F, 0.325F)
                quadToRelative(-0.475F, 0.0F, -0.8F, -0.325F)
                quadToRelative(-0.325F, -0.325F, -0.325F, -0.8F)
                quadToRelative(0.0F, -0.475F, 0.325F, -0.8F)
                lineToRelative(4.8F, -4.8F)
                lineToRelative(-4.8F, -4.8F)
                quadToRelative(-0.325F, -0.325F, -0.325F, -0.8F)
                quadToRelative(0.0F, -0.475F, 0.325F, -0.8F)
                quadToRelative(0.325F, -0.325F, 0.8F, -0.325F)
                quadToRelative(0.475F, 0.0F, 0.8F, 0.325F)
                lineToRelative(4.8F, 4.8F)
                lineToRelative(4.8F, -4.8F)
                quadToRelative(0.325F, -0.325F, 0.8F, -0.325F)
                quadToRelative(0.475F, 0.0F, 0.8F, 0.325F)
                quadToRelative(0.325F, 0.325F, 0.325F, 0.8F)
                quadToRelative(0.0F, 0.475F, -0.325F, 0.8F)
                lineTo(13.6F, 12.0F)
                lineToRelative(4.8F, 4.8F)
                quadToRelative(0.325F, 0.325F, 0.325F, 0.8F)
                quadToRelative(0.0F, 0.475F, -0.325F, 0.8F)
                quadToRelative(-0.325F, 0.325F, -0.8F, 0.325F)
                quadToRelative(-0.475F, 0.0F, -0.8F, -0.325F)
                close()
            }
        }
        return _close!!
    }

private var _close: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconClosePreview() {
    Image(imageVector = AppIcon.Close, contentDescription = null)
}