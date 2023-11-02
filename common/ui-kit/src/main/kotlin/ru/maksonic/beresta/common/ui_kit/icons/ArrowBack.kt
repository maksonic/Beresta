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
val AppIcon.ArrowBack: ImageVector
    get() {
        if (_arrowBack != null) {
            return _arrowBack!!
        }
        _arrowBack = materialIcon(name = "ArrowBack") {
            materialPath {
                moveTo(19.0F, 11.0F)
                horizontalLineTo(7.83F)
                lineToRelative(4.88F, -4.88F)
                curveToRelative(0.39F, -0.39F, 0.39F, -1.03F, 0.0F, -1.42F)
                curveToRelative(-0.39F, -0.39F, -1.02F, -0.39F, -1.41F, 0.0F)
                lineToRelative(-6.59F, 6.59F)
                curveToRelative(-0.39F, 0.39F, -0.39F, 1.02F, 0.0F, 1.41F)
                lineToRelative(6.59F, 6.59F)
                curveToRelative(0.39F, 0.39F, 1.02F, 0.39F, 1.41F, 0.0F)
                curveToRelative(0.39F, -0.39F, 0.39F, -1.02F, 0.0F, -1.41F)
                lineTo(7.83F, 13.0F)
                horizontalLineTo(19.0F)
                curveToRelative(0.55F, 0.0F, 1.0F, -0.45F, 1.0F, -1.0F)
                reflectiveCurveToRelative(-0.45F, -1.0F, -1.0F, -1.0F)
                close()
            }
        }
        return _arrowBack!!
    }

private var _arrowBack: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconArrowBackPreview() {
    Image(imageVector = AppIcon.ArrowBack, contentDescription = null)
}