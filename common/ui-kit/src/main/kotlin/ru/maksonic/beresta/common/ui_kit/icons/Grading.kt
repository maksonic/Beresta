package ru.maksonic.beresta.common.ui_kit.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 08.07.2023
 */
val AppIcon.Grading: ImageVector
    get() {
        if (_grading != null) {
            return _grading!!
        }
        _grading = ImageVector.Builder(
            name = "Grading",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 960.0F,
            viewportHeight = 960.0F,
        ).materialPath {
            moveTo(160.0F, 360.0F)
            quadToRelative(-18.52F, 0.0F, -31.06F, -12.53F)
            quadToRelative(-12.53F, -12.53F, -12.53F, -31.06F)
            quadToRelative(0.0F, -18.43F, 12.53F, -31.13F)
            quadToRelative(12.53F, -12.69F, 31.06F, -12.69F)
            horizontalLineToRelative(640.0F)
            quadToRelative(18.52F, 0.0F, 31.06F, 12.69F)
            quadToRelative(12.53F, 12.7F, 12.53F, 31.13F)
            quadToRelative(0.0F, 18.43F, -12.53F, 31.01F)
            quadTo(818.52F, 360.0F, 800.0F, 360.0F)
            lineTo(160.0F, 360.0F)

            moveTo(160.0F, 523.59F)
            quadToRelative(-18.52F, 0.0F, -31.06F, -12.53F)
            quadToRelative(-12.53F, -12.53F, -12.53F, -31.06F)
            reflectiveQuadToRelative(12.53F, -31.06F)
            quadToRelative(12.53F, -12.53F, 31.06F, -12.53F)
            horizontalLineToRelative(640.0F)
            quadToRelative(18.52F, 0.0F, 31.06F, 12.53F)
            quadToRelative(12.53F, 12.53F, 12.53F, 31.06F)
            reflectiveQuadToRelative(-12.53F, 31.06F)
            quadTo(818.52F, 523.59F, 800.0F, 523.59F)
            lineTo(160.0F, 523.59F)

            moveTo(160.0F, 687.41F)
            quadToRelative(-18.52F, 0.0F, -31.06F, -12.69F)
            quadToRelative(-12.53F, -12.7F, -12.53F, -31.13F)
            quadToRelative(0.0F, -18.43F, 12.53F, -31.01F)
            quadTo(141.48F, 600.0F, 160.0F, 600.0F)
            horizontalLineToRelative(276.41F)
            quadToRelative(18.52F, 0.0F, 31.06F, 12.53F)
            quadTo(480.0F, 625.06F, 480.0F, 643.59F)
            quadToRelative(0.0F, 18.43F, -12.53F, 31.13F)
            quadToRelative(-12.53F, 12.69F, -31.06F, 12.69F)
            lineTo(160.0F, 687.41F)

            moveTo(160.0F, 851.0F)
            quadToRelative(-18.52F, 0.0F, -31.06F, -12.53F)
            quadToRelative(-12.53F, -12.53F, -12.53F, -31.06F)
            quadToRelative(0.0F, -18.43F, 12.53F, -31.13F)
            quadToRelative(12.53F, -12.7F, 31.06F, -12.7F)
            horizontalLineToRelative(276.41F)
            quadToRelative(18.52F, 0.0F, 31.06F, 12.7F)
            quadTo(480.0F, 788.98F, 480.0F, 807.41F)
            reflectiveQuadToRelative(-12.53F, 31.01F)
            quadTo(454.94F, 851.0F, 436.41F, 851.0F)
            lineTo(160.0F, 851.0F)

            moveTo(624.93F, 819.37F)
            lineTo(566.5F, 760.93F)
            quadToRelative(-12.2F, -12.2F, -12.2F, -30.51F)
            quadToRelative(0.0F, -18.32F, 12.2F, -30.51F)
            quadToRelative(12.2F, -12.2F, 30.51F, -12.2F)
            quadToRelative(18.32F, 0.0F, 30.51F, 12.2F)
            lineToRelative(28.04F, 27.8F)
            lineToRelative(96.37F, -96.37F)
            quadToRelative(12.19F, -12.2F, 30.63F, -12.2F)
            reflectiveQuadToRelative(30.63F, 12.2F)
            quadToRelative(12.19F, 12.2F, 12.19F, 30.51F)
            quadToRelative(0.0F, 18.32F, -12.19F, 30.51F)
            lineToRelative(-127.0F, 127.0F)
            quadToRelative(-12.96F, 12.96F, -30.63F, 12.96F)
            reflectiveQuadToRelative(-30.63F, -12.96F)

            moveTo(160.0F, 196.41F)
            quadToRelative(-18.52F, 0.0F, -31.06F, -12.7F)
            quadToRelative(-12.53F, -12.69F, -12.53F, -31.13F)
            reflectiveQuadToRelative(12.53F, -31.01F)
            quadTo(141.48F, 109.0F, 160.0F, 109.0F)
            horizontalLineToRelative(640.0F)
            quadToRelative(18.52F, 0.0F, 31.06F, 12.53F)
            quadToRelative(12.53F, 12.53F, 12.53F, 31.06F)
            quadToRelative(0.0F, 18.43F, -12.53F, 31.13F)
            quadToRelative(-12.53F, 12.7F, -31.06F, 12.7F)
            lineTo(160.0F, 196.41F)
            close()
        }.build()
        return _grading!!
    }
private var _grading: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconGradingPreview() {
    Image(imageVector = AppIcon.Grading, contentDescription = null)
}