package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.Lock: ImageVector
    get() {
        if (_lock != null) {
            return _lock!!
        }
        _lock = materialIcon(name = "Lock") {
            materialPath {
                moveTo(6.075F, 22.175F)
                quadToRelative(-0.95F, 0.0F, -1.612F, -0.662F)
                quadTo(3.8F, 20.85F, 3.8F, 19.9F)
                verticalLineToRelative(-9.85F)
                quadToRelative(0.0F, -0.925F, 0.663F, -1.6F)
                quadToRelative(0.662F, -0.675F, 1.612F, -0.675F)
                lineTo(6.9F, 7.775F)
                lineTo(6.9F, 5.95F)
                quadToRelative(0.0F, -2.125F, 1.487F, -3.613F)
                quadTo(9.875F, 0.85F, 12.0F, 0.85F)
                reflectiveQuadToRelative(3.613F, 1.487F)
                quadTo(17.1F, 3.825F, 17.1F, 5.95F)
                verticalLineToRelative(1.825F)
                horizontalLineToRelative(0.825F)
                quadToRelative(0.95F, 0.0F, 1.613F, 0.675F)
                quadToRelative(0.662F, 0.675F, 0.662F, 1.6F)
                verticalLineToRelative(9.85F)
                quadToRelative(0.0F, 0.95F, -0.662F, 1.613F)
                quadToRelative(-0.663F, 0.662F, -1.613F, 0.662F)

                moveTo(6.075F, 19.9F)
                horizontalLineToRelative(11.85F)
                verticalLineToRelative(-9.85F)
                lineTo(6.075F, 10.05F)
                verticalLineToRelative(9.85F)

                moveTo(12.0F, 16.975F)
                quadToRelative(0.825F, 0.0F, 1.413F, -0.588F)
                quadTo(14.0F, 15.8F, 14.0F, 14.975F)
                reflectiveQuadToRelative(-0.587F, -1.413F)
                quadToRelative(-0.588F, -0.587F, -1.413F, -0.587F)
                quadToRelative(-0.825F, 0.0F, -1.412F, 0.587F)
                quadTo(10.0F, 14.15F, 10.0F, 14.975F)
                quadToRelative(0.0F, 0.825F, 0.588F, 1.412F)
                quadToRelative(0.587F, 0.588F, 1.412F, 0.588F)

                moveTo(9.175F, 7.775F)
                horizontalLineToRelative(5.65F)
                lineTo(14.825F, 5.95F)
                quadToRelative(0.0F, -1.175F, -0.825F, -2.0F)
                reflectiveQuadToRelative(-2.0F, -0.825F)
                quadToRelative(-1.175F, 0.0F, -2.0F, 0.825F)
                reflectiveQuadToRelative(-0.825F, 2.0F)

                moveTo(6.075F, 19.9F)
                verticalLineToRelative(-9.85F)
                close()
            }
        }
        return _lock!!
    }

private var _lock: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconLockPreview() {
    Image(imageVector = AppIcon.Lock, contentDescription = null)
}