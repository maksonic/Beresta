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
val AppIcon.Add: ImageVector
    get() {
        if (_add != null) {
            return _add!!
        }
        _add = materialIcon(name = "Add") {
            materialPath {
                moveTo(18.0F, 13.0F)
                horizontalLineToRelative(-5.0F)
                verticalLineToRelative(5.0F)
                curveToRelative(0.0F, 0.55F, -0.45F, 1.0F, -1.0F, 1.0F)
                reflectiveCurveToRelative(-1.0F, -0.45F, -1.0F, -1.0F)
                verticalLineToRelative(-5.0F)
                horizontalLineTo(6.0F)
                curveToRelative(-0.55F, 0.0F, -1.0F, -0.45F, -1.0F, -1.0F)
                reflectiveCurveToRelative(0.45F, -1.0F, 1.0F, -1.0F)
                horizontalLineToRelative(5.0F)
                verticalLineTo(6.0F)
                curveToRelative(0.0F, -0.55F, 0.45F, -1.0F, 1.0F, -1.0F)
                reflectiveCurveToRelative(1.0F, 0.45F, 1.0F, 1.0F)
                verticalLineToRelative(5.0F)
                horizontalLineToRelative(5.0F)
                curveToRelative(0.55F, 0.0F, 1.0F, 0.45F, 1.0F, 1.0F)
                reflectiveCurveToRelative(-0.45F, 1.0F, -1.0F, 1.0F)
                close()
            }
        }
        return _add!!
    }

private var _add: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconAddPreview() {
    Image(imageVector = AppIcon.Add, contentDescription = null)
}