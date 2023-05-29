package ru.maksonic.beresta.ui.theme.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme

/**
 * @Author maksonic on 26.05.2023
 */
val AppImage.MaksonicDef: ImageVector
    get() {
        if (_maksonicDef != null) {
            return _maksonicDef!!
        }
        _maksonicDef = ImageVector.Builder(
            name = "MaksonicDef",
            defaultWidth = 100.0.dp,
            defaultHeight = 100.0.dp,
            viewportWidth = 100.0F,
            viewportHeight = 100.0F,
        ).path(
            fill = SolidColor(Color(0xFFCC0000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(100.0F, 50.0F)
            curveTo(100.0F, 77.61F, 77.61F, 100.0F, 50.0F, 100.0F)
            curveTo(22.39F, 100.0F, 0.0F, 77.61F, 0.0F, 50.0F)
            curveTo(0.0F, 22.39F, 22.39F, 0.0F, 50.0F, 0.0F)
            curveTo(77.61F, 0.0F, 100.0F, 22.39F, 100.0F, 50.0F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFFD700)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(76.53F, 23.13F)
            lineTo(76.53F, 77.85F)
            curveTo(76.53F, 79.53F, 75.16F, 80.89F, 73.48F, 80.89F)
            curveTo(71.8F, 80.89F, 70.44F, 79.53F, 70.44F, 77.85F)
            lineTo(70.44F, 30.47F)
            lineTo(52.45F, 48.46F)
            curveTo(51.27F, 49.64F, 49.34F, 49.64F, 48.15F, 48.45F)
            curveTo(46.96F, 47.27F, 46.96F, 45.34F, 48.15F, 44.15F)
            lineTo(71.33F, 20.98F)
            curveTo(72.2F, 20.11F, 73.51F, 19.85F, 74.65F, 20.32F)
            curveTo(75.79F, 20.79F, 76.53F, 21.9F, 76.53F, 23.13F)

            moveTo(43.85F, 52.76F)
            curveTo(40.28F, 49.19F, 40.28F, 43.41F, 43.85F, 39.85F)
            lineTo(67.03F, 16.67F)
            curveTo(69.64F, 14.06F, 73.57F, 13.28F, 76.98F, 14.7F)
            curveTo(80.39F, 16.11F, 82.61F, 19.44F, 82.61F, 23.13F)
            lineTo(82.61F, 77.85F)
            curveTo(82.61F, 82.89F, 78.52F, 86.98F, 73.48F, 86.98F)
            curveTo(68.44F, 86.98F, 64.35F, 82.89F, 64.35F, 77.85F)
            lineTo(64.35F, 45.16F)
            lineTo(56.76F, 52.76F)
            curveTo(53.19F, 56.32F, 47.41F, 56.32F, 43.85F, 52.76F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFFD700)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(27.13F, 14.0F)
            curveTo(32.17F, 14.0F, 36.26F, 18.09F, 36.26F, 23.13F)
            verticalLineTo(46.3F)
            curveTo(36.26F, 51.35F, 32.17F, 55.43F, 27.13F, 55.43F)
            curveTo(22.09F, 55.43F, 18.0F, 51.35F, 18.0F, 46.3F)
            verticalLineTo(23.13F)
            curveTo(18.0F, 18.09F, 22.09F, 14.0F, 27.13F, 14.0F)

            moveTo(30.17F, 23.13F)
            curveTo(30.17F, 21.45F, 28.81F, 20.09F, 27.13F, 20.09F)
            curveTo(25.45F, 20.09F, 24.09F, 21.45F, 24.09F, 23.13F)
            verticalLineTo(46.3F)
            curveTo(24.09F, 47.98F, 25.45F, 49.35F, 27.13F, 49.35F)
            curveTo(28.81F, 49.35F, 30.17F, 47.98F, 30.17F, 46.3F)
            verticalLineTo(23.13F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFFD700)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(29.54F, 16.95F)
            curveTo(30.73F, 15.76F, 32.65F, 15.76F, 33.84F, 16.95F)
            lineTo(43.18F, 26.26F)
            lineTo(52.81F, 35.9F)
            lineTo(48.5F, 40.2F)
            lineTo(38.88F, 30.57F)
            lineTo(38.88F, 30.56F)
            lineTo(29.54F, 21.25F)
            curveTo(28.35F, 20.07F, 28.35F, 18.14F, 29.54F, 16.95F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFFD700)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(31.07F, 35.69F)
            curveTo(32.26F, 34.5F, 34.19F, 34.5F, 35.38F, 35.69F)
            lineTo(48.43F, 48.75F)
            curveTo(49.62F, 49.94F, 49.61F, 51.87F, 48.42F, 53.06F)
            curveTo(47.24F, 54.24F, 45.31F, 54.24F, 44.12F, 53.05F)
            lineTo(31.07F, 40.0F)
            curveTo(29.89F, 38.81F, 29.89F, 36.88F, 31.07F, 35.69F)
            close()
        }.build()
        return _maksonicDef!!
    }
private var _maksonicDef: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconMaksonicDefPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.MaksonicDef, contentDescription = null)

    }
}