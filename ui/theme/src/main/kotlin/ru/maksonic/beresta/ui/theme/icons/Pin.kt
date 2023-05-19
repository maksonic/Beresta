package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.Pin: ImageVector
    get() {
        if (_pin != null) {
            return _pin!!
        }
        _pin = materialIcon(name = "Pin") {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(14.427F, 2.059F)
                curveTo(14.708F, 1.778F, 15.163F, 1.778F, 15.444F, 2.059F)
                lineTo(20.925F, 7.54F)
                curveTo(21.206F, 7.821F, 21.206F, 8.277F, 20.925F, 8.558F)
                lineTo(20.142F, 9.341F)
                curveTo(19.861F, 9.622F, 19.405F, 9.622F, 19.124F, 9.341F)
                lineTo(13.644F, 3.86F)
                curveTo(13.362F, 3.579F, 13.362F, 3.123F, 13.644F, 2.842F)
                lineTo(14.427F, 2.059F)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(4.248F, 9.106F)
                curveTo(4.529F, 8.825F, 4.985F, 8.825F, 5.266F, 9.106F)
                lineTo(13.878F, 17.719F)
                curveTo(14.16F, 18.0F, 14.16F, 18.455F, 13.878F, 18.736F)
                lineTo(13.095F, 19.519F)
                curveTo(12.814F, 19.8F, 12.359F, 19.8F, 12.078F, 19.519F)
                lineTo(3.465F, 10.907F)
                curveTo(3.184F, 10.625F, 3.184F, 10.17F, 3.465F, 9.889F)
                lineTo(4.248F, 9.106F)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(11.036F, 7.015F)
                lineTo(14.035F, 4.016F)
                lineTo(15.836F, 5.817F)
                lineTo(12.313F, 9.341F)
                curveTo(11.972F, 9.681F, 11.47F, 9.8F, 11.013F, 9.649F)
                curveTo(10.208F, 9.384F, 9.777F, 9.391F, 9.379F, 9.539F)
                curveTo(8.899F, 9.718F, 8.272F, 10.173F, 7.255F, 11.265F)
                lineTo(5.391F, 9.53F)
                curveTo(6.453F, 8.389F, 7.421F, 7.551F, 8.49F, 7.153F)
                curveTo(9.369F, 6.826F, 10.203F, 6.833F, 11.036F, 7.015F)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.EvenOdd,
            ) {
                moveTo(15.969F, 11.948F)
                lineTo(18.968F, 8.949F)
                lineTo(17.167F, 7.148F)
                lineTo(13.644F, 10.672F)
                curveTo(13.303F, 11.012F, 13.184F, 11.515F, 13.335F, 11.971F)
                curveTo(13.601F, 12.777F, 13.593F, 13.207F, 13.445F, 13.605F)
                curveTo(13.266F, 14.085F, 12.811F, 14.713F, 11.719F, 15.729F)
                lineTo(13.454F, 17.593F)
                curveTo(14.596F, 16.531F, 15.433F, 15.564F, 15.831F, 14.494F)
                curveTo(16.159F, 13.615F, 16.151F, 12.781F, 15.969F, 11.948F)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.3F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(7.018F, 14.239F)
                lineTo(6.912F, 14.133F)
                lineTo(6.806F, 14.239F)
                lineTo(2.5F, 18.545F)
                lineTo(2.456F, 18.589F)
                lineTo(2.456F, 18.651F)
                lineTo(2.456F, 20.217F)
                lineTo(2.456F, 20.367F)
                lineTo(2.606F, 20.367F)
                horizontalLineTo(4.172F)
                lineTo(4.234F, 20.367F)
                lineTo(4.278F, 20.323F)
                lineTo(8.584F, 16.017F)
                lineTo(8.69F, 15.911F)
                lineTo(8.584F, 15.805F)
                lineTo(7.018F, 14.239F)
                close()
            }
        }
        return _pin!!
    }

private var _pin: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconPinPreview() {
    Image(imageVector = AppIcon.Pin, contentDescription = null)
}