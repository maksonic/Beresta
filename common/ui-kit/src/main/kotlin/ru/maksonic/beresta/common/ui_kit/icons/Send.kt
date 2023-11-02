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
val AppIcon.Send: ImageVector
    get() {
        if (_send != null) {
            return _send!!
        }
        _send = materialIcon(name = "Send") {
            materialPath {
                moveTo(2.825F, 18.475F)
                verticalLineTo(5.525F)
                quadToRelative(0.0F, -0.625F, 0.513F, -0.963F)
                quadToRelative(0.512F, -0.337F, 1.062F, -0.087F)
                lineToRelative(15.35F, 6.475F)
                quadToRelative(0.7F, 0.3F, 0.7F, 1.05F)
                reflectiveQuadToRelative(-0.7F, 1.05F)
                lineTo(4.4F, 19.525F)
                quadToRelative(-0.55F, 0.25F, -1.062F, -0.087F)
                quadToRelative(-0.513F, -0.338F, -0.513F, -0.963F)

                moveTo(5.0F, 16.9F)
                lineTo(16.625F, 12.0F)
                lineTo(5.0F, 7.1F)
                verticalLineToRelative(3.3F)
                lineToRelative(6.1F, 1.6F)
                lineTo(5.0F, 13.6F)

                moveTo(5.0F, 12.0F)
                verticalLineTo(7.1F)
                verticalLineToRelative(9.8F)
                close()
            }
        }
        return _send!!
    }

private var _send: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconSendPreview() {
    Image(imageVector = AppIcon.Send, contentDescription = null)
}